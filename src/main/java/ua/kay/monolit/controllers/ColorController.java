package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.models.SprColor;
import ua.kay.monolit.repositories.ColorRepository;

import java.util.List;

@RestController
@RequestMapping("/color")
public class ColorController {

    @Autowired
    ColorRepository colorRepository;

    @RequestMapping("/{id}")
    public List<SprColor> getProductColors(@PathVariable("id") Integer id) {
        return colorRepository.findColorsByProductCategoryId(id);
    }

}
