package ua.kay.monolit.controllers;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.models.FullCategory;
import ua.kay.monolit.models.SprCategory;
import ua.kay.monolit.repositories.FullCategoryRepository;

@RestController
@RequestMapping("/full_category")
public class FullCategoryController {

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    FullCategoryRepository fullCategoryRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveCategory(@RequestBody FullCategory fullCategory){
        System.out.println(fullCategory);
//        return fullCategoryRepository.save(fullCategory);
    }

}
