package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.models.SprColor;
import ua.kay.monolit.repositories.ColorRepository;

import java.util.List;

@RestController
public class ColorController {

    @Autowired
    ColorRepository colorRepository;

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
        colorRepository.delete(id);
    }

}
