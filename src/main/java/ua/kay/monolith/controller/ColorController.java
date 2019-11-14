package ua.kay.monolith.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolith.model.SprColor;
import ua.kay.monolith.repository.ColorRepository;
import ua.kay.monolith.service.ColorService;

import java.util.List;

@RestController
public class ColorController {

    private ColorRepository colorRepository;
    private ColorService colorService;

    @Autowired
    public ColorController(ColorRepository colorRepository, ColorService colorService) {
        this.colorRepository = colorRepository;
        this.colorService = colorService;
    }

    @RequestMapping("/color/all")
    public List<SprColor> findAllColors(){
        return colorRepository.findAll();
    }

    @RequestMapping("/color/{id}")
    public List<SprColor> getProductColors(@PathVariable("id") Integer id) {
        return colorRepository.findColorsByProductCategoryId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/color/save", method = RequestMethod.POST)
    public SprColor saveColor(@RequestBody SprColor sprColor){
        return colorRepository.saveAndFlush(sprColor);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/color/delete/{id}", method = RequestMethod.DELETE)
    public void deleteColor(@PathVariable Integer id){
        colorService.delete(id);
    }

}
