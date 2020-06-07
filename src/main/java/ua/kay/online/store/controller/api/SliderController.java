package ua.kay.online.store.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.online.store.model.Slider;
import ua.kay.online.store.repository.SliderRepository;

import java.util.List;

@RestController
public class SliderController  {

    SliderRepository sliderRepository;

    @RequestMapping("/slide/all")
    public List<Slider> findAllSlide(){
        return sliderRepository.findAllOrderByImage();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/slide/save", method = RequestMethod.POST)
    public Slider saveSlide(@RequestBody Slider slider){
        return sliderRepository.saveAndFlush(slider);
    }

}
