package ua.kay.online.store.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.online.store.model.Color;
import ua.kay.online.store.repository.ColorRepository;
import ua.kay.online.store.service.ColorServiceImpl;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class ColorController {

    private ColorRepository colorRepository;
    private ColorServiceImpl colorServiceImpl;

    public ColorController(ColorRepository colorRepository, ColorServiceImpl colorServiceImpl) {
        this.colorRepository = colorRepository;
        this.colorServiceImpl = colorServiceImpl;
    }

    @RequestMapping("/color/all")
    public List<Color> findAllColors(){
        return colorRepository.findAll();
    }

    @RequestMapping("/color/{id}")
    public Stream<Color> getProductColors(@PathVariable("id") Long id) {
        return colorRepository.findColorsByProductCategoryId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/color/save", method = RequestMethod.POST)
    public Color saveColor(@RequestBody Color color){
        return colorRepository.saveAndFlush(color);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/color/delete/{id}", method = RequestMethod.DELETE)
    public void deleteColor(@PathVariable Long id){
        colorServiceImpl.deleteById(id);
    }
}
