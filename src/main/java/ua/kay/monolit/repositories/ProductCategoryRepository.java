package ua.kay.monolit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.models.SprCategory;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<SprCategory, Integer> {
    @Async
    List<SprCategory> findByParentIdOrderByNameAsc(Integer id);

    @Async
    List<SprCategory> findByParentIdNotOrderByNameAsc(Integer id);

    @Async
    SprCategory findByIdCategory(Integer id);
}
