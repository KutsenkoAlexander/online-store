package ua.kay.online.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.online.store.model.Size;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SizeRepository extends JpaRepository<Size, Long> {

    @Query("select p.size from Product p " +
            "where p.category.id = ?1 " +
            "group by p.size.name, p.size.id")
    List<Size> findSizesByProductCategoryId(Long categoryId);

    @Async
    void deleteById(Long id);
}
