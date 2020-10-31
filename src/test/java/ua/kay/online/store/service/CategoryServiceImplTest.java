package ua.kay.online.store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ua.kay.online.store.model.Category;
import ua.kay.online.store.repository.CategoryRepository;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

@SpringBootTest
class CategoryServiceImplTest {

    private CategoryServiceImpl productCategoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productCategoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void getRootCategory() {
        Long parentId = 0L;
        Category category = new Category();
        category.setParentId(parentId);
        when(categoryRepository.findByParentIdOrderByNameAsc(parentId)).thenReturn(List.of(category));
        List<Category> categories = productCategoryService.getRootCategory();
        assertEquals(categories.size(), 1);
        verify(categoryRepository, times(1)).findByParentIdOrderByNameAsc(parentId);
    }
}
