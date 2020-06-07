package ua.kay.online.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.online.store.dto.SearchResultProductDto;
import ua.kay.online.store.model.Product;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query("select p from Product p where (p.category.id = ?1 or ?1 is null) " +
                                    "and (p.consumer.id = ?2  or ?2 is null) " +
                                    "and (p.type.id = ?3 or ?3 is null) " +
                                    "and (p.size.id = ?4 or ?4 is null) " +
                                    "and (p.color.id = ?5 or ?5 is null) " +
                                    "and (p.exist = ?6 or ?6 is null)")
    Page<Product> sortProduct(Long categoryId,
                              Long consumerId,
                              Long typeId,
                              Long sizeId,
                              Long colorId,
                              Byte exist,
                              Pageable pageable);

    Optional<Product> findById(Long id);

    Page<Product> findProductByCategoryId(Long id, Pageable pageable);

    @Query("select p from Product p order by p.title")
    Page<Product> findAllOrderByTitle(Pageable pageable);

    @Query("select new ua.kay.monolith.dto.SearchResultProductDto(p.id, p.title) " +
            "from Product p where p.title like %?1%")
    Stream<SearchResultProductDto> findTitleLikeName(String name);

    @Async
    void deleteById(Long id);
}
