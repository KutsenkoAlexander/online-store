package ua.kay.online.store.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.online.store.model.Color;
import ua.kay.online.store.service.ColorServiceImpl;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/colors")
public class ColorController {

    private final ColorServiceImpl colorServiceImpl;

    public ColorController(ColorServiceImpl colorServiceImpl) {
        this.colorServiceImpl = colorServiceImpl;
    }

    @GetMapping
    public List<Color> findAllColors(){
        return colorServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Set<Color> getProductColors(@PathVariable("id") Long id) {
        return colorServiceImpl.findColorsByProductCategoryId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Color saveColor(@RequestBody Color color){
        return colorServiceImpl.save(color);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void deleteColor(@PathVariable Long id){
        colorServiceImpl.deleteById(id);
    }
}
