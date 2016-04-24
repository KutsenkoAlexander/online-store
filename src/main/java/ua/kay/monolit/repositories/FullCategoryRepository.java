package ua.kay.monolit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.models.FullCategory;

import java.util.List;

public interface FullCategoryRepository extends JpaRepository<FullCategory, Long> {
    @Async
    @Query("select i from FullCategory i " +
            "where i.sprCategoryByCategoryId.parentId = ?1 " +
            "order by i.sprCategoryByCategoryId.name")
    List<FullCategory> findByParentIdWithImg(Integer parentId);
}
