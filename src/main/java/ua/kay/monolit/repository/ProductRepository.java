package ua.kay.monolit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.model.Product;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Async
    @Query("select p from Product p where (p.sprCategory.idCategory = ?1 or ?1 is null) " +
                                    "and (p.sprConsumer.idConsumer = ?2  or ?2 is null) " +
                                    "and (p.sprType.idSprType = ?3 or ?3 is null) " +
                                    "and (p.sprSize.idSprSize = ?4 or ?4 is null) " +
                                    "and (p.sprColor.idSprColors = ?5 or ?5 is null) " +
                                    "and (p.exist = ?6 or ?6 is null)")
    Page<Product> sortProduct(Integer categoryId,
                              Long consumerId,
                              Integer typeId,
                              Integer sizeId,
                              Integer colorId,
                              Byte exist,
                              Pageable pageable);

    @Async
    Product findByIdProduct(Long productId);

    @Async
    Page<Product> findProductBySprCategoryIdCategory(Integer idCategory, Pageable pageable);

    @Async
    @Query("select p from Product p order by p.title")
    Page<Product> findAllOrderByTitle(Pageable pageable);

    @Async
    @Query("select new ua.kay.monolit.dto.SearchResultProductDto(p.idProduct, p.title) from Product p where p.title like %?1%")
    List<Product> findTitleLikeName(String name);

}
