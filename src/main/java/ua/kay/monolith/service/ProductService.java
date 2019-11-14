package ua.kay.monolith.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.kay.monolith.dto.SearchResultProductDto;
import ua.kay.monolith.model.Product;
import ua.kay.monolith.model.SprCategory;
import ua.kay.monolith.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> sortProduct(Integer categoryId, Long consumerId, Integer typeId, Integer sizeId, Integer colorId, Byte exist, Pageable pageable) {
        return productRepository.sortProduct(categoryId, consumerId, typeId, sizeId, colorId, exist, pageable);
    }

    public Product findByIdProduct(Long id) {
        Product product = productRepository.findByIdProduct(id);
        SprCategory sprCategory = product.getSprCategory();
        sprCategory.setImage(null);
        return product;
    }

    public Page<Product> findAllOrderByTitle(Pageable pageable) {
        Page<Product> products = productRepository.findAllOrderByTitle(pageable);
        for (Product p : products){
            SprCategory sprCategory = p.getSprCategory();
            sprCategory.setImage(null);
        }
        return products;
    }

    public Page<Product> findProductBySprCategoryIdCategory(Integer id, Pageable pageable) {
        Page<Product> products = productRepository.findProductBySprCategoryIdCategory(id, pageable);
        for (Product p : products){
            SprCategory sprCategory = p.getSprCategory();
            sprCategory.setImage(null);
        }
        return products;
    }

    public List<SearchResultProductDto> findTitleLikeName(String name) {
        return productRepository.findTitleLikeName(name);
    }

    public Product save(Product product) {
        return  productRepository.save(product);
    }

    public void deleteByIdProduct(Long id) {
        productRepository.deleteByIdProduct(id);
    }
}
