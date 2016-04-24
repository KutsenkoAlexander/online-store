package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.models.SprType;
import ua.kay.monolit.repositories.TypeRepository;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    TypeRepository typeRepository;

    @RequestMapping("/{id}")
    public List<SprType> getProductTypes(@PathVariable("id") Integer id) {
        return typeRepository.findTypesByProductCategoryId(id);
    }
}
