package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.models.SprType;
import ua.kay.monolit.repositories.TypeRepository;

import java.util.List;

@RestController
public class TypeController {

    @Autowired
    TypeRepository typeRepository;

    @RequestMapping("/type/all")
    public List<SprType> findAllTypes(){
        return typeRepository.findAll();
    }

    @RequestMapping("/type/{id}")
    public List<SprType> getProductTypes(@PathVariable("id") Integer id) {
        return typeRepository.findTypesByProductCategoryId(id);
    }

    @RequestMapping(value = "/rest/type/save", method = RequestMethod.POST)
    public SprType saveType(@RequestBody SprType sprType){
        return typeRepository.saveAndFlush(sprType);
    }

    @RequestMapping(value = "/rest/type/delete/{id}", method = RequestMethod.DELETE)
    public void deleteType(@PathVariable Integer id){
        typeRepository.delete(id);
    }

}
