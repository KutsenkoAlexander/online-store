package ua.kay.monolit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.dto.BreadcrumbsDto;
import ua.kay.monolit.model.SprCategory;
import ua.kay.monolit.repository.ProductCategoryRepository;
import ua.kay.monolit.service.ProductCategoryService;

import java.util.List;

@RestController
public class ProductCategoryController {

    private static final int PARENT_ID = 0;

    @Autowired
    ProductCategoryRepository categoryRepository;

    @Autowired
    ProductCategoryService categoryService;

    @RequestMapping("/catalog/category/{id}")
    public List<SprCategory> getByParentIdOrderByNameAsc(@PathVariable("id") Integer id) {
        return categoryRepository.findByParentIdOrderByNameAsc(id);
    }

    @RequestMapping("/catalog/category/name/{id}")
    public BreadcrumbsDto getCategoryById(@PathVariable("id") Integer id) {
        return categoryService.getCategoryForBreadcrumbs(id);
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/category/save", method = RequestMethod.POST)
    public SprCategory saveCategory(@RequestBody SprCategory category){
        return categoryRepository.saveAndFlush(category);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/category/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable Integer id){
        categoryRepository.delete(id);
    }

}
