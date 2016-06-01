package ua.kay.monolit.controllers;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.kay.monolit.models.Image;
import ua.kay.monolit.repositories.ImageRepository;

import java.io.IOException;

@RestController
public class ImageController {

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    ImageRepository imageRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/rest/image/save", method = RequestMethod.POST)
    public Image saveImages(@RequestParam("file") MultipartFile file,
                            @RequestParam("idImage") Long idImage){
        Image image = beanFactory.getBean(Image.class);
        try {
            image.setIdImage(idImage);
            image.setImage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image savedImage = imageRepository.saveAndFlush(image);
        return savedImage;
    }

}
