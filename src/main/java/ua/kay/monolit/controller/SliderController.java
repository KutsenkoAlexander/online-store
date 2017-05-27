package ua.kay.monolit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.model.Slider;
import ua.kay.monolit.repository.SliderRepository;

import java.util.List;

@RestController
public class SliderController  {

    @Autowired
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
