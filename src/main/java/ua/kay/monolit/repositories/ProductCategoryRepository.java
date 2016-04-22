package ua.kay.monolit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.models.ImgCategory;
import ua.kay.monolit.models.SprCategory;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<SprCategory, Integer> {
    @Async
    List<SprCategory> findByParentIdOrderByNameAsc(Integer id);

    @Async
    SprCategory findByIdCategory(Integer id);

    @Async
    List<SprCategory> findByParentIdNotOrderByNameAsc(Integer parentId);

    @Async
    @Query("select i from ImgCategory i join fetch i.sprCategoryByCategoryId " +
            "where i.sprCategoryByCategoryId.parentId = ?1 " +
            "order by i.sprCategoryByCategoryId.name")
    List<ImgCategory> findParentCategoryWithImg(Integer parentId);

    @Async
    @Query("select i from ImgCategory i join fetch i.sprCategoryByCategoryId " +
            "where i.sprCategoryByCategoryId.parentId <> ?1 " +
            "order by i.sprCategoryByCategoryId.name")
    List<ImgCategory> findChildCategoryWithImg(Integer parentId);

}
