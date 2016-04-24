package ua.kay.monolit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.models.SprConsumer;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<SprConsumer, Long> {
    @Async
    @Query("select p.sprConsumer from Product p where p.sprCategory.idCategory = ?1 group by p.sprConsumer.name")
    List<SprConsumer> findConsumerByProductCategoryId(Integer categoryId);
}
