package ua.kay.online.store.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.kay.online.store.model.Image;
import ua.kay.online.store.repository.ImageRepository;

@AllArgsConstructor
@RestController
public class ImageController {

    private final BeanFactory beanFactory;
    private final ImageRepository imageRepository;

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
