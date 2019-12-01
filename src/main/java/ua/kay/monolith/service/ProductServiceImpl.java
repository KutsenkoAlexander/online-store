package ua.kay.monolith.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.kay.monolith.dto.SearchResultProductDto;
import ua.kay.monolith.exceptions.ObjectNotFoundException;
import ua.kay.monolith.model.Product;
import ua.kay.monolith.model.Category;
import ua.kay.monolith.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> sortProduct(Long categoryId, Long consumerId, Long typeId, Long sizeId, Long colorId, Byte exist, Pageable pageable) {
        return productRepository.sortProduct(categoryId, consumerId, typeId, sizeId, colorId, exist, pageable);
    }

    public Product findByIdProduct(Long id) throws ObjectNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product not find by id ", id));
        Category category = product.getCategory();
        category.setImage(null);
        return product;
    }

    public Page<Product> findAllOrderByTitle(Pageable pageable) {
        Page<Product> products = productRepository.findAllOrderByTitle(pageable);
        for (Product p : products){
            Category category = p.getCategory();
            category.setImage(null);
        }
        return products;
    }

    public Page<Product> findProductBySprCategoryIdCategory(Long id, Pageable pageable) {
        Page<Product> products = productRepository.findProductByCategoryId(id, pageable);
        for (Product p : products){
            Category category = p.getCategory();
            category.setImage(null);
        }
        return products;
    }

    public Stream<SearchResultProductDto> findTitleLikeName(String name) {
        return productRepository.findTitleLikeName(name);
    }

    @Transactional
    public Product save(Product product) {
        return  productRepository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
