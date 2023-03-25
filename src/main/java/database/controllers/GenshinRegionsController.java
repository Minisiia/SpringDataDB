package database.controllers;

import database.models.GenshinRegion;
import database.services.GenshinRegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/genshin-regions")
public class GenshinRegionsController {

    private final GenshinRegionsService genshinRegionsService;

    @Autowired
    public GenshinRegionsController(GenshinRegionsService genshinRegionsService) {
        this.genshinRegionsService = genshinRegionsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("regions", genshinRegionsService.findAll());
        return "genshin-regionss/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("region", genshinRegionsService.findOne(id));
        return "genshin-regionss/show";
    }

    @GetMapping("/new")
    public String newHero(@ModelAttribute("region") GenshinRegion genshinRegion) {
        return "genshin-regionss/new";
    }

    @GetMapping("/index")
    public String index(@RequestParam(value = "myVariable", required = false) String myVariable,
                        Model model) {
        model.addAttribute("regions", genshinRegionsService.findAll());
        model.addAttribute("listOfHeroes", genshinRegionsService.getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(myVariable));
        return "genshin-regionss/index";
    }

    @PostMapping("/my-form")
    public String find(@RequestParam("myVariable") String myVariable, Model model) {
        model.addAttribute("myVariable", myVariable);
        model.addAttribute("listOfHeroes", genshinRegionsService.getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(myVariable));
        return "redirect:/genshin-regions/index";
    }


    @PostMapping()
    public String create(@ModelAttribute("region") @Valid GenshinRegion genshinRegion,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "genshin-regionss/new";

        genshinRegionsService.save(genshinRegion);
        return "redirect:/genshin-regions";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("region", genshinRegionsService.findOne(id));
        return "genshin-regionss/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("region") @Valid GenshinRegion genshinRegion, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "genshin-regionss/edit";

        genshinRegionsService.update(id, genshinRegion);
        return "redirect:/genshin-regions";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        genshinRegionsService.delete(id);
        return "redirect:/genshin-regions";
    }
}
