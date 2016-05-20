package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.models.SprSize;
import ua.kay.monolit.repositories.SizeRepository;

import java.util.List;

@RestController
public class SizeController {

    @Autowired
    SizeRepository sizeRepository;

    @RequestMapping("/size/all")
    public List<SprSize> findAllSizes(){
        return sizeRepository.findAll();
    }

    @RequestMapping("/size/{id}")
    public List<SprSize> getProductSizes(@PathVariable("id") Integer id) {
        return sizeRepository.findSizesByProductCategoryId(id);
    }

    @RequestMapping(value = "/rest/size/save", method = RequestMethod.POST)
    public SprSize saveSize(@RequestBody SprSize sprSize){
        return sizeRepository.saveAndFlush(sprSize);
    }

    @RequestMapping(value = "/rest/size/delete/{id}", method = RequestMethod.DELETE)
    public void deleteSize(@PathVariable Integer id){
        sizeRepository.delete(id);
    }

}
