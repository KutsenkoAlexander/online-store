package ua.kay.monolith.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolith.model.Type;
import ua.kay.monolith.repository.TypeRepository;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class TypeController {

    @Autowired
    TypeRepository typeRepository;

    @RequestMapping("/type/all")
    public List<Type> findAllTypes(){
        return typeRepository.findAll();
    }

    @RequestMapping("/type/{id}")
    public Stream<Type> getProductTypes(@PathVariable("id") Long id) {
        return typeRepository.findTypesByProductCategoryId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/type/save", method = RequestMethod.POST)
    public Type saveType(@RequestBody Type type){
        return typeRepository.saveAndFlush(type);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/type/delete/{id}", method = RequestMethod.DELETE)
    public void deleteType(@PathVariable Long id){
        typeRepository.deleteById(id);
    }

}
