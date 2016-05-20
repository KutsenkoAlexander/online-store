package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.models.Product;
import ua.kay.monolit.models.SprCategory;
import ua.kay.monolit.repositories.ProductRepository;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/product/sort/{categoryId}", method = RequestMethod.GET, produces = {"application/json"})
    public PagedResources<Product> sortProduct(@PathVariable("categoryId") Integer categoryId,
                                               @PathParam("consumerId") Long consumerId,
                                               @PathParam("typeId") Integer typeId,
                                               @PathParam("sizeId") Integer sizeId,
                                               @PathParam("colorId") Integer colorId,
                                               @PathParam("exist") Byte exist,
                                               Pageable pageable,
                                               PagedResourcesAssembler assembler) {
        Page<Product> products =  productRepository.sortProduct(categoryId, consumerId, typeId, sizeId, colorId, exist, pageable);
        return assembler.toResource(products);
    }

    @RequestMapping("/product/product_id/{id}")
    public Product findByIdProduct(@PathVariable("id") Long id) {
        Product product = productRepository.findByIdProduct(id);
        SprCategory sprCategory = product.getSprCategory();
        sprCategory.setImage(null);
        return product;
    }

    @RequestMapping(value = "/product/all", method = RequestMethod.GET, produces = {"application/json"})
    public PagedResources<Product> getAllProductsForEdit(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Product> products = productRepository.findAllOrderByTitle(pageable);
        for (Product p : products){
            SprCategory sprCategory = p.getSprCategory();
            sprCategory.setImage(null);
        }
        return assembler.toResource(products);
    }

    @RequestMapping(value = "/product/category/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public PagedResources<Product> getProducts(@PathVariable("id") Integer id, Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Product> products = productRepository.findProductBySprCategoryIdCategory(id, pageable);
        for (Product p : products){
            SprCategory sprCategory = p.getSprCategory();
            sprCategory.setImage(null);
        }
        return assembler.toResource(products);
    }

    @RequestMapping("/product/search")
    public List<Product> searchProductByName(@PathParam(value="name") String name) {
        return productRepository.findTitleLikeName(name);
    }

    @RequestMapping(value = "/rest/product/save", method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @RequestMapping(value = "/rest/product/delete/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id){
        productRepository.delete(id);
    }

}
