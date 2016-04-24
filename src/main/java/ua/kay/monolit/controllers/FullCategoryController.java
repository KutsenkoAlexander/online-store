package ua.kay.monolit.controllers;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.models.FullCategory;
import ua.kay.monolit.repositories.FullCategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/full_category")
public class FullCategoryController {

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    FullCategoryRepository fullCategoryRepository;

}
