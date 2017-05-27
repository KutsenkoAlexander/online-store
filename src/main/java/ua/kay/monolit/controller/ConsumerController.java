package ua.kay.monolit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.model.SprConsumer;
import ua.kay.monolit.repository.ConsumerRepository;

import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    ConsumerRepository consumerRepository;

    @RequestMapping("/consumer/all")
    public List<SprConsumer> findAllConsumers(){
        return consumerRepository.findAll();
    }

    @RequestMapping("/consumer/{id}")
    public List<SprConsumer> getProductConsumers(@PathVariable("id") Integer id) {
        return consumerRepository.findConsumerByProductCategoryId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/consumer/save", method = RequestMethod.POST)
    public SprConsumer saveConsumer(@RequestBody SprConsumer sprConsumer){
        return consumerRepository.saveAndFlush(sprConsumer);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/consumer/delete/{id}", method = RequestMethod.DELETE)
    public void deleteConsumer(@PathVariable Long id){
        consumerRepository.delete(id);
    }

}
