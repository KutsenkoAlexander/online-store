package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.models.SprCategory;
import ua.kay.monolit.repositories.ProductCategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/catalog/category")
public class ProductCategoryController {

    private static final int PARENT_ID = 0;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @RequestMapping("/{id}")
    public List<SprCategory> getByParentIdOrderByNameAsc(@PathVariable("id") Integer id) {
        return productCategoryRepository.findByParentIdOrderByNameAsc(id);
    }

    @RequestMapping("/name/{id}")
    public SprCategory getCategoryById(@PathVariable("id") Integer id) {
        SprCategory sprCategory = productCategoryRepository.findByIdCategory(id);
        return sprCategory;
    }

    @RequestMapping("/parent")
    public List<SprCategory> findParentCategory(){
        List<SprCategory> sprCategories = productCategoryRepository.findByParentIdOrderByNameAsc(PARENT_ID);
        for(SprCategory sprCategory : sprCategories){
            sprCategory.setImage(null);
        }
        return sprCategories;
    }

    @RequestMapping("/parent_with_img")
    public List<SprCategory> findParentCategoryWithImg(){
        List<SprCategory> sprCategories = productCategoryRepository.findByParentIdOrderByNameAsc(PARENT_ID);
        return sprCategories;
    }

    @RequestMapping("/child")
    public List<SprCategory> findCategoryByParentId(){
        List<SprCategory> sprCategories = productCategoryRepository.findByParentIdNotOrderByNameAsc(PARENT_ID);
        for(SprCategory sprCategory : sprCategories){
            sprCategory.setImage(null);
        }
        return sprCategories;
    }

    @RequestMapping("/child_with_img/{id}")
    public List<SprCategory> findCategoryByParentIdWithImg(@PathVariable("id") Integer id){
        List<SprCategory> sprCategories = productCategoryRepository.findByParentIdOrderByNameAsc(id);
        return sprCategories;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public SprCategory saveCategory(@RequestBody SprCategory category){
        return productCategoryRepository.save(category);
    }

}
