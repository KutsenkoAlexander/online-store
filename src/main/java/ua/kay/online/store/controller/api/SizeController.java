package ua.kay.online.store.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.online.store.model.Size;
import ua.kay.online.store.service.SizeService;

import java.util.List;

@AllArgsConstructor
@RestController
public class SizeController {

    private final SizeService sizeService;

    @RequestMapping("/size/all")
    public List<Size> findAllSizes(){
        return sizeService.findAll();
    }

    @RequestMapping("/size/{id}")
    public List<Size> getProductSizes(@PathVariable("id") Long id) {
        return sizeService.findSizesByProductCategoryId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/size/save", method = RequestMethod.POST)
    public Size saveSize(@RequestBody Size size){
        return sizeService.save(size);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/size/delete/{id}", method = RequestMethod.DELETE)
    public void deleteSize(@PathVariable Long id){
        sizeService.delete(id);
    }

}
