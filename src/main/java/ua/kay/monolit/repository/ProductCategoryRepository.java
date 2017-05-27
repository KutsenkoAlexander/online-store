package ua.kay.monolit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.dto.BreadcrumbsDto;
import ua.kay.monolit.model.SprCategory;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<SprCategory, Integer> {
    @Async
    List<SprCategory> findByParentIdOrderByNameAsc(Integer id);

    @Async
    List<SprCategory> findByParentIdNotOrderByNameAsc(Integer id);

    @Async
    @Query("select new ua.kay.monolit.dto.BreadcrumbsDto(c.idCategory, c.name, c.parentId) " +
            "from SprCategory c where c.idCategory = ?1")
    BreadcrumbsDto getCategoryForBreadcrumbs(Integer id);
}
