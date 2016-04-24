package ua.kay.monolit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.models.SprSize;

import java.util.List;

public interface SizeRepository extends JpaRepository<SprSize, Long> {
    @Async
    @Query("select p.sprSize from Product p where p.sprCategory.idCategory = ?1 group by p.sprSize.name")
    List<SprSize> findSizesByProductCategoryId(Integer categoryId);
}
