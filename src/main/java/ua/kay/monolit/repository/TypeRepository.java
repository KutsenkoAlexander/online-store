package ua.kay.monolit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.model.SprType;

import java.util.List;

public interface TypeRepository extends JpaRepository<SprType, Integer> {
    @Async
    @Query("select p.sprType from Product p where p.sprCategory.idCategory = ?1 group by p.sprType.name")
    List<SprType> findTypesByProductCategoryId(Integer categoryId);
}
