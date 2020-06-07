package ua.kay.online.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.online.store.dto.BreadcrumbsDto;
import ua.kay.online.store.model.Category;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Stream<Category> findByParentIdOrderByNameAsc(Long id);

    Stream<Category> findByParentIdNotOrderByNameAsc(Long id);

    @Query("select new ua.kay.online.store.dto.BreadcrumbsDto(c.id, c.name, c.parentId) " +
            "from Category c where c.id = ?1")
    Optional<BreadcrumbsDto> getCategoryForBreadcrumbs(Long id);

    @Async
    void deleteById(Long id);
}
