package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.models.ImgCategory;
import ua.kay.monolit.models.SprCategory;
import ua.kay.monolit.repositories.ProductCategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/catalog/category")
public class ProductCategoryController {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @RequestMapping
    public List<SprCategory> getAll() {
        return productCategoryRepository.findByParentIdOrderByNameAsc(0);
    }

    @RequestMapping("/{id}")
    public List<SprCategory> getByParentIdOrderByNameAsc(@PathVariable("id") Integer id) {
        return productCategoryRepository.findByParentIdOrderByNameAsc(id);
    }

    @RequestMapping("/name/{id}")
    public SprCategory getCategoryById(@PathVariable("id") Integer id) {
        SprCategory sprCategory = productCategoryRepository.findByIdCategory(id);
        return sprCategory;
    }

    @RequestMapping("/child")
    public List<SprCategory> findCategoryByParentId(){
        List<SprCategory> sprCategories = productCategoryRepository.findByParentIdNotOrderByNameAsc(0);
        return sprCategories;
    }

    @RequestMapping("/parent")
    public List<SprCategory> findParentCategory(){
        List<SprCategory> sprCategories = productCategoryRepository.findByParentIdOrderByNameAsc(0);
        return sprCategories;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public SprCategory saveCategory(@RequestBody SprCategory category){
        return productCategoryRepository.save(category);
    }

    @RequestMapping("/parent_with_img")
    public List<ImgCategory> findAllImgCategoryJoinCategory(){
        return productCategoryRepository.findCategoryByParentIdWithImg(0);
    }
}
