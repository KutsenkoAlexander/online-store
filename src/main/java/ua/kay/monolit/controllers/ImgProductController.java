package ua.kay.monolit.controllers;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.kay.monolit.models.ImgProduct;
import ua.kay.monolit.repositories.ImgProductRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/img_product")
public class ImgProductController {

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    ImgProductRepository imgProductRepository;

    @RequestMapping("/all")
    public List<ImgProduct> findAllImgProduct(){
        return imgProductRepository.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ImgProduct saveImages(@RequestParam("file") MultipartFile file){
        ImgProduct images = beanFactory.getBean(ImgProduct.class);
        try {
            images.setImage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgProductRepository.save(images);
    }

}
