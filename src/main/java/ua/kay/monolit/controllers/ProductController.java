package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.models.FullProduct;
import ua.kay.monolit.models.Product;
import ua.kay.monolit.repositories.ProductRepository;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/product_id/{id}")
    public FullProduct findByIdProduct(@PathVariable("id") Long id) {
        return productRepository.findByIdProduct(id);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public PagedResources<Product> getProducts(@PathVariable("id") Integer id, Pageable pageable, PagedResourcesAssembler assembler) {
        Page<FullProduct> products = productRepository.findByCategoryId(id, pageable);
        return assembler.toResource(products);
    }

    @RequestMapping("/consumers/{id}")
    public List<Product> getProductConsumers(@PathVariable("id") Integer id) {
        return productRepository.findConsumerByCategoryId(id);
    }

    @RequestMapping("/types/{id}")
    public List<Product> getProductTypes(@PathVariable("id") Integer id) {
        return productRepository.findTypeProductByCategoryId(id);
    }

    @RequestMapping("/sizes/{id}")
    public List<Product> getProductSizes(@PathVariable("id") Integer id) {
        return productRepository.findSizeProductByCategoryId(id);
    }

    @RequestMapping("/colors/{id}")
    public List<Product> getProductColors(@PathVariable("id") Integer id) {
        return productRepository.findColorProductByCategoryId(id);
    }

    @RequestMapping("/price/{id}")
    public List<Product> getProductPrices(@PathVariable("id") Integer id) {
        return productRepository.findPricesProductByCategoryId(id);
    }

    @RequestMapping("/exist/{id}")
    public List<Product> getProductExist(@PathVariable("id") Integer id) {
        return productRepository.findExistProductByCategoryId(id);
    }

    @RequestMapping(value = "/sort/{categoryId}", method = RequestMethod.GET, produces = {"application/json"})
    public PagedResources<FullProduct> sortProduct(@PathVariable("categoryId") Integer categoryId,
                                     @PathParam("consumerId") Long consumerId,
                                     @PathParam("typeId") Integer typeId,
                                     @PathParam("sizeId") Integer sizeId,
                                     @PathParam("colorId") Integer colorId,
                                     @PathParam("exist") Byte exist,
                                     Pageable pageable,
                                     PagedResourcesAssembler assembler) {
        Page<FullProduct> products =  productRepository.sortProduct(categoryId, consumerId, typeId, sizeId, colorId, exist, pageable);
        return assembler.toResource(products);
    }

    @RequestMapping("/search")
    public List<Product> searchProductByName(@PathParam(value="name") String name) {
        return productRepository.findTitleLikeName(name);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public PagedResources<Product> getAllProductsForEdit(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Product> products = productRepository.findAll(pageable);
        return assembler.toResource(products);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

}
