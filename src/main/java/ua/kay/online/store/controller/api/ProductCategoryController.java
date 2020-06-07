package ua.kay.online.store.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.online.store.dto.BreadcrumbsDto;
import ua.kay.online.store.exception.ObjectNotFoundException;
import ua.kay.online.store.model.Category;
import ua.kay.online.store.service.CategoryServiceImpl;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class ProductCategoryController {

    private final CategoryServiceImpl categoryService;

    @RequestMapping("/parent/{id}")
    public List<Category> getByParentIdOrderByNameAsc(@PathVariable("id") Long id) {
        return categoryService.getByParentIdOrderByNameAsc(id);
    }

    @RequestMapping("/{id}")
    public BreadcrumbsDto getCategoryById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return categoryService.getCategoryById(id);
    }

    @RequestMapping("/parent")
    public List<Category> findParentCategory(){
        return categoryService.getRootCategory();
    }

    @RequestMapping("/parent_with_img")
    public List<Category> findParentCategoryWithImg(){
        return null; // categoryRepository.findByParentIdOrderByNameAsc(PARENT_ID);
    }

    @RequestMapping("/catalog/category/child_with_img/{id}")
    public List<Category> findCategoryByParentIdWithImg(@PathVariable("id") Integer id){
        return null; //categoryRepository.findByParentIdOrderByNameAsc(id);
    }

    @RequestMapping("/catalog/category/child")
    public List<Category> findCategoryByParentId(){
//        List<SprCategory> sprCategories = categoryRepository.findByParentIdNotOrderByNameAsc(PARENT_ID);
//        for(SprCategory sprCategory : sprCategories){
//            sprCategory.setImage(null);
//        }
        return null; //sprCategories;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/category/save", method = RequestMethod.POST)
    public Category saveCategory(@RequestBody Category category){
        return categoryService.save(category);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/category/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
    }
}
