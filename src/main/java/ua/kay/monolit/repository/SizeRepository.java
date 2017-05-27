package ua.kay.monolit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.model.SprSize;

import java.util.List;

public interface SizeRepository extends JpaRepository<SprSize, Integer> {
    @Async
    @Query("select p.sprSize from Product p where p.sprCategory.idCategory = ?1 group by p.sprSize.name")
    List<SprSize> findSizesByProductCategoryId(Integer categoryId);
}
