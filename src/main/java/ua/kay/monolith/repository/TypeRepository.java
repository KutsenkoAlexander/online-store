package ua.kay.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.monolith.model.Type;

import java.util.stream.Stream;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query("select p.type from Product p " +
            "where p.category.id = ?1 " +
            "group by p.type.name, p.type.id")
    Stream<Type> findTypesByProductCategoryId(Long categoryId);

    @Async
    void deleteById(Long id);
}
