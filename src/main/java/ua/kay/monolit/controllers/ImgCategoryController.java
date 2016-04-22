package ua.kay.monolit.controllers;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.models.ImgCategory;
import ua.kay.monolit.models.SprCategory;
import ua.kay.monolit.repositories.ImgCategoryRepository;
import java.io.IOException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/img_category")
public class ImgCategoryController {

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    ImgCategoryRepository imgCategoryRepository;

    @RequestMapping("/all")
    public List<ImgCategory> findAllImgCategory(){
        return imgCategoryRepository.findAll();
    }

//    @RequestMapping("/name/{id}")
//    public SprCategory getCategoryById(@PathVariable("id") Integer id) {
//        SprCategory sprCategory = imgCategoryRepository.findByIdCategory(id);
//        return sprCategory;
//    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ImgCategory saveImages(@RequestParam("file") MultipartFile file){
        ImgCategory images = beanFactory.getBean(ImgCategory.class);
        try {
            images.setImages(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgCategoryRepository.save(images);
    }

}
