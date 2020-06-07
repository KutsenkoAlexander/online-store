package ua.kay.online.store.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.online.store.model.Size;
import ua.kay.online.store.repository.SizeRepository;
import ua.kay.online.store.service.SizeService;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class SizeController {

    private SizeRepository sizeRepository;
    private SizeService sizeService;

    public SizeController(SizeRepository sizeRepository, SizeService sizeService) {
        this.sizeRepository = sizeRepository;
        this.sizeService = sizeService;
    }

    @RequestMapping("/size/all")
    public List<Size> findAllSizes(){
        return sizeRepository.findAll();
    }

    @RequestMapping("/size/{id}")
    public Stream<Size> getProductSizes(@PathVariable("id") Long id) {
        return sizeRepository.findSizesByProductCategoryId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/size/save", method = RequestMethod.POST)
    public Size saveSize(@RequestBody Size size){
        return sizeRepository.saveAndFlush(size);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/size/delete/{id}", method = RequestMethod.DELETE)
    public void deleteSize(@PathVariable Long id){
        sizeService.delete(id);
    }

}
