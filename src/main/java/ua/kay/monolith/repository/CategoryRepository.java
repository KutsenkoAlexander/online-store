package ua.kay.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.monolith.dto.BreadcrumbsDto;
import ua.kay.monolith.model.Category;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Stream<Category> findByParentIdOrderByNameAsc(Long id);

    Stream<Category> findByParentIdNotOrderByNameAsc(Long id);

    @Query("select new ua.kay.monolith.dto.BreadcrumbsDto(c.id, c.name, c.parentId) " +
            "from Category c where c.id = ?1")
    Optional<BreadcrumbsDto> getCategoryForBreadcrumbs(Long id);

    @Async
    void deleteById(Long id);
}
