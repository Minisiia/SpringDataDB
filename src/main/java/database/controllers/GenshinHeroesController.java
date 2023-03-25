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

    @PostMapping("/my-form")
    public String findWeapon(@RequestParam("myLocation") String myLocation,
                             @RequestParam("myWeapon") String myWeapon,
                             Model model) {
        model.addAttribute("myLocation", myLocation);
        model.addAttribute("myWeapon", myWeapon);
        model.addAttribute("weaponList", genshinHeroesService.getGenshinHeroesWithSomeWeapon(myLocation, myWeapon));
        return "redirect:/genshin-heroes/index";
    }

    @GetMapping("/index")
    public String index(@RequestParam(value = "myName", required = false) String myName,
                        @RequestParam(value = "myLocation", required = false) String myLocation,
                        @RequestParam(value = "myWeapon", required = false) String myWeapon,
                        @RequestParam(value = "myRarity", required = false) Integer myRarity,
                        Model model) {
        model.addAttribute("heroes", genshinHeroesService.findAll());
        model.addAttribute("heroesByNameList", genshinHeroesService.findByName(myName));
        model.addAttribute("weaponList", genshinHeroesService.getGenshinHeroesWithSomeWeapon(myLocation, myWeapon));
        model.addAttribute("heroesByRarityList", genshinHeroesService.findByRarity(myRarity));
        return "genshin-heroes/index";
    }

    @PostMapping("/find-heroes-by-name")
    public String findByName(@RequestParam("myName") String myName, Model model) {
        model.addAttribute("myName", myName);
        model.addAttribute("heroesByNameList", genshinHeroesService.findByName(myName));
        return "redirect:/genshin-heroes/index";
    }

    @PostMapping("/find-heroes-by-rarity")
    public String findByRarity(@RequestParam("myRarity") Integer myRarity, Model model) {
        model.addAttribute("myRarity", myRarity);
        model.addAttribute("heroesByRarityList", genshinHeroesService.findByRarity(myRarity));
        return "redirect:/genshin-heroes/index";
    }

    @PostMapping("/update-hero-name-by-id")
    public String updateHeroNameById(@RequestParam("myUpdateName") String myUpdateName,
                                     @RequestParam("myUpdateId") int myUpdateId,
                                     Model model) {
        model.addAttribute("myUpdateName", myUpdateName);
        model.addAttribute("myUpdateId", myUpdateId);
        genshinHeroesService.updateHeroNameById(myUpdateId, myUpdateName);
        return "redirect:/genshin-heroes/index";
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

    @PostMapping("/delete-by-name")
    public String deleteByName(@RequestParam("myDeleteName") String myDeleteName) {
        genshinHeroesService.deleteByName(myDeleteName);
        return "redirect:/genshin-heroes";
    }

}
