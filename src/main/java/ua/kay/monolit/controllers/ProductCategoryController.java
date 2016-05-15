package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.models.SprCategory;
import ua.kay.monolit.repositories.ProductCategoryRepository;

import java.util.List;

@RestController
public class ProductCategoryController {

    private static final int PARENT_ID = 0;

    @Autowired
    ProductCategoryRepository categoryRepository;

    @RequestMapping("/catalog/category/{id}")
    public List<SprCategory> getByParentIdOrderByNameAsc(@PathVariable("id") Integer id) {
        return categoryRepository.findByParentIdOrderByNameAsc(id);
    }

    @RequestMapping("/catalog/category/name/{id}")
    public SprCategory getCategoryById(@PathVariable("id") Integer id) {
        return categoryRepository.findByIdCategory(id);
    }

    @RequestMapping("/catalog/category/parent")
    public List<SprCategory> findParentCategory(){
        List<SprCategory> sprCategories = categoryRepository.findByParentIdOrderByNameAsc(PARENT_ID);
        for(SprCategory sprCategory : sprCategories){
            sprCategory.setImage(null);
        }
        return sprCategories;
    }

    @RequestMapping("/catalog/category/parent_with_img")
    public List<SprCategory> findParentCategoryWithImg(){
        return categoryRepository.findByParentIdOrderByNameAsc(PARENT_ID);
    }

    @RequestMapping("/catalog/category/child")
    public List<SprCategory> findCategoryByParentId(){
        List<SprCategory> sprCategories = categoryRepository.findByParentIdNotOrderByNameAsc(PARENT_ID);
        for(SprCategory sprCategory : sprCategories){
            sprCategory.setImage(null);
        }
        return sprCategories;
    }

    @RequestMapping("/catalog/category/child_with_img/{id}")
    public List<SprCategory> findCategoryByParentIdWithImg(@PathVariable("id") Integer id){
        return categoryRepository.findByParentIdOrderByNameAsc(id);
    }

    @RequestMapping(value = "/admin/category/save", method = RequestMethod.POST)
    public SprCategory saveCategory(@RequestBody SprCategory category){
        return categoryRepository.saveAndFlush(category);
    }

    @RequestMapping(value = "/admin/category/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable Integer id){
        categoryRepository.delete(id);
    }

}
