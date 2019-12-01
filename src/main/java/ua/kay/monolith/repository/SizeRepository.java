package ua.kay.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.monolith.model.Size;

import java.util.stream.Stream;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    @Query("select p.size from Product p " +
            "where p.category.id = ?1 " +
            "group by p.size.name, p.size.id")
    Stream<Size> findSizesByProductCategoryId(Long categoryId);

    @Async
    void deleteById(Long id);
}
