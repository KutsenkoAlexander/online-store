package ua.kay.monolith.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.monolith.dto.BreadcrumbsDto;
import ua.kay.monolith.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public BreadcrumbsDto getCategoryForBreadcrumbs(Integer id){
         return productCategoryRepository.getCategoryForBreadcrumbs(id);
    }

}
