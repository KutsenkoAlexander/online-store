package ua.kay.monolith.controller.api;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.kay.monolith.model.Image;
import ua.kay.monolith.repository.ImageRepository;

import java.io.IOException;

@RestController
public class ImageController {

    private BeanFactory beanFactory;
    private ImageRepository imageRepository;

    public ImageController(BeanFactory beanFactory, ImageRepository imageRepository) {
        this.beanFactory = beanFactory;
        this.imageRepository = imageRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/rest/image/save", method = RequestMethod.POST)
    public Image saveImages(@RequestParam("file") MultipartFile file,
                            @RequestParam("idImage") Long idImage){
        Image image = beanFactory.getBean(Image.class);
//        try {
            image.setId(idImage);
//            image.setImage(file.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return imageRepository.saveAndFlush(image);
    }
}
