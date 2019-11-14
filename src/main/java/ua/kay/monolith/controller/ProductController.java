package ua.kay.monolith.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolith.dto.SearchResultProductDto;
import ua.kay.monolith.model.Product;
import ua.kay.monolith.service.ProductService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin
    @RequestMapping(value = "/product/sort/{categoryId}", method = RequestMethod.GET, produces = {"application/json"})
    public Page<Product> sortProduct(@PathVariable("categoryId") Integer categoryId,
                                         @PathParam("consumerId") Long consumerId,
                                         @PathParam("typeId") Integer typeId,
                                         @PathParam("sizeId") Integer sizeId,
                                         @PathParam("colorId") Integer colorId,
                                         @PathParam("exist") Byte exist,
                                         Pageable pageable) {
        return productService.sortProduct(categoryId, consumerId, typeId, sizeId, colorId, exist, pageable);
    }

    @RequestMapping("/product/product_id/{id}")
    public Product findByIdProduct(@PathVariable("id") Long id) {
        return productService.findByIdProduct(id);
    }

    @RequestMapping(value = "/product/all", method = RequestMethod.GET, produces = {"application/json"})
    public Iterable<Product> getAllProductsForEdit(Pageable pageable) {
        return productService.findAllOrderByTitle(pageable);
    }

    @RequestMapping(value = "/product/category/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Iterable<Product> getProducts(@PathVariable("id") Integer id, Pageable pageable) {
        return productService.findProductBySprCategoryIdCategory(id, pageable);
    }

    @RequestMapping("/product/search")
    public List<SearchResultProductDto> searchProductByName(@PathParam(value="name") String name) {
        return productService.findTitleLikeName(name);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/product/save", method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/product/delete/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id){
        productService.deleteByIdProduct(id);
    }
}
