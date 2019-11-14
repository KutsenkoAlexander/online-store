package ua.kay.monolith.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolith.model.SprSize;
import ua.kay.monolith.repository.SizeRepository;
import ua.kay.monolith.service.SizeService;

import java.util.List;

@RestController
public class SizeController {

    private SizeRepository sizeRepository;
    private SizeService sizeService;

    @Autowired
    public SizeController(SizeRepository sizeRepository, SizeService sizeService) {
        this.sizeRepository = sizeRepository;
        this.sizeService = sizeService;
    }

    @RequestMapping("/size/all")
    public List<SprSize> findAllSizes(){
        return sizeRepository.findAll();
    }

    @RequestMapping("/size/{id}")
    public List<SprSize> getProductSizes(@PathVariable("id") Integer id) {
        return sizeRepository.findSizesByProductCategoryId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/size/save", method = RequestMethod.POST)
    public SprSize saveSize(@RequestBody SprSize sprSize){
        return sizeRepository.saveAndFlush(sprSize);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/size/delete/{id}", method = RequestMethod.DELETE)
    public void deleteSize(@PathVariable Integer id){
        sizeService.delete(id);
    }

}
