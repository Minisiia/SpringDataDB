package database.controllers;

import database.models.GenshinHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import database.services.GenshinHeroesService;

import javax.validation.Valid;

@Controller
@RequestMapping("/genshin-heroes")
public class GenshinHeroesController {

    private final GenshinHeroesService genshinHeroesService;

    @Autowired
    public GenshinHeroesController(GenshinHeroesService genshinHeroesService) {
        this.genshinHeroesService = genshinHeroesService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("heroes", genshinHeroesService.findAll());
        return "genshin-heroes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("hero", genshinHeroesService.findOne(id));
        return "genshin-heroes/show";
    }

    @GetMapping("/new")
    public String newHero(@ModelAttribute("hero") GenshinHero genshinHero) {
        return "genshin-heroes/new";
    }

    @GetMapping("/heroes-with-some-weapon")
    public String heroesFromTeyvat(@RequestParam("myWeapon") String myWeapon, Model model) {
        model.addAttribute("weaponList", genshinHeroesService.getGenshinHeroesWithSomeWeapon(myWeapon));
        return "genshin-heroes/heroes-with-some-weapon";
    }

    @PostMapping("/my-form")
    public String findWeapon(@RequestParam("myWeapon") String myWeapon, Model model) {
        model.addAttribute("myWeapon", myWeapon);
        model.addAttribute("weaponList", genshinHeroesService.getGenshinHeroesWithSomeWeapon(myWeapon));
        return "redirect:/genshin-heroes/heroes-with-some-weapon";
    }

    @PostMapping()
    public String create(@ModelAttribute("hero") @Valid GenshinHero genshinHero,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "genshin-heroes/new";

        genshinHeroesService.save(genshinHero);
        return "redirect:/genshin-heroes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("hero", genshinHeroesService.findOne(id));
        return "genshin-heroes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("hero") @Valid GenshinHero genshinHero, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "genshin-heroes/edit";

        genshinHeroesService.update(id, genshinHero);
        return "redirect:/genshin-heroes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        genshinHeroesService.delete(id);
        return "redirect:/genshin-heroes";
    }


}
