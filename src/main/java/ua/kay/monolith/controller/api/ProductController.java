package ua.kay.monolith.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolith.dto.SearchResultProductDto;
import ua.kay.monolith.exception.ObjectNotFoundException;
import ua.kay.monolith.model.Product;
import ua.kay.monolith.service.ProductServiceImpl;

import javax.websocket.server.PathParam;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @CrossOrigin
    @RequestMapping(value = "/sort/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Page<Product> sortProduct(@PathVariable("id") Long categoryId,
                                         @PathParam("consumerId") Long consumerId,
                                         @PathParam("typeId") Long typeId,
                                         @PathParam("sizeId") Long sizeId,
                                         @PathParam("colorId") Long colorId,
                                         @PathParam("exist") Byte exist,
                                         Pageable pageable) {
        return productServiceImpl.sortProduct(categoryId, consumerId, typeId, sizeId, colorId, exist, pageable);
    }

    @RequestMapping("/{id}")
    public Product findByIdProduct(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return productServiceImpl.findByIdProduct(id);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Iterable<Product> getProducts(@PathVariable("id") Long id, Pageable pageable) {
        return productServiceImpl.findProductBySprCategoryIdCategory(id, pageable);
    }

    @RequestMapping("/search")
    public Stream<SearchResultProductDto> searchProductByName(@PathParam(value="name") String name) {
        return productServiceImpl.findTitleLikeName(name);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    public Iterable<Product> getAllProductsForEdit(Pageable pageable) {
        return productServiceImpl.findAllOrderByTitle(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product){
        return productServiceImpl.save(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id){
        productServiceImpl.delete(id);
    }
}
