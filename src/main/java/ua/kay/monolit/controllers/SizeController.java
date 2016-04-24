package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.models.SprSize;
import ua.kay.monolit.repositories.SizeRepository;

import java.util.List;

@RestController
@RequestMapping("/size")
public class SizeController {

    @Autowired
    SizeRepository sizeRepository;

    @RequestMapping("/{id}")
    public List<SprSize> getProductSizes(@PathVariable("id") Integer id) {
        return sizeRepository.findSizesByProductCategoryId(id);
    }

}
