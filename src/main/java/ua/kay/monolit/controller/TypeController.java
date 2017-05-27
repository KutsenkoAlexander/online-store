package ua.kay.monolit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.model.SprType;
import ua.kay.monolit.repository.TypeRepository;

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/type/save", method = RequestMethod.POST)
    public SprType saveType(@RequestBody SprType sprType){
        return typeRepository.saveAndFlush(sprType);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/type/delete/{id}", method = RequestMethod.DELETE)
    public void deleteType(@PathVariable Integer id){
        typeRepository.delete(id);
    }

}
