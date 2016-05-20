package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.models.Slider;
import ua.kay.monolit.repositories.SliderRepository;

import java.util.List;

@RestController
public class SliderController  {

    @Autowired
    SliderRepository sliderRepository;

    @RequestMapping("/slide/all")
    public List<Slider> findAllSlide(){
        return sliderRepository.findAllOrderByImage();
    }

    @RequestMapping(value = "/rest/slide/save", method = RequestMethod.POST)
    public Slider saveSlide(@RequestBody Slider slider){
        return sliderRepository.saveAndFlush(slider);
    }

}
