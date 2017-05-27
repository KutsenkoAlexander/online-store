package ua.kay.monolit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.monolit.dto.BreadcrumbsDto;
import ua.kay.monolit.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public BreadcrumbsDto getCategoryForBreadcrumbs(Integer id){
         return productCategoryRepository.getCategoryForBreadcrumbs(id);
    }

}
