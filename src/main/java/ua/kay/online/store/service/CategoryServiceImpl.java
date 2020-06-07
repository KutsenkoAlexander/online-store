package ua.kay.online.store.service;

import org.springframework.stereotype.Service;
import ua.kay.online.store.dto.BreadcrumbsDto;
import ua.kay.online.store.exception.ObjectNotFoundException;
import ua.kay.online.store.model.Category;
import ua.kay.online.store.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryServiceImpl implements CrudService<Category> {

    private static final Long PARENT_ID = 0L;

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Stream<Category> findAll() {
        return categoryRepository.findAll().stream();
    }

    @Override
    public Category findById(Long id) throws ObjectNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found by id ", id));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public BreadcrumbsDto getCategoryById(Long id) throws ObjectNotFoundException {
        return categoryRepository.getCategoryForBreadcrumbs(id)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found by id ", id));
    }

    public List<Category> getByParentIdOrderByNameAsc(Long id) {
        return categoryRepository.findByParentIdOrderByNameAsc(id)
                .peek(e -> e.setImage(null))
                .collect(Collectors.toList());
    }

    public List<Category> getRootCategory(){
        return getByParentIdOrderByNameAsc(PARENT_ID);
    }
}
