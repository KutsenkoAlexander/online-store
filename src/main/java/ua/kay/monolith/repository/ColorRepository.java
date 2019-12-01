package ua.kay.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.monolith.model.Color;

import java.util.stream.Stream;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query("select p.color from Product p " +
            "where p.category.id = ?1 " +
            "group by p.color.name, p.color.id")
    Stream<Color> findColorsByProductCategoryId(Long id);

    @Async
    void deleteById(Long id);
}