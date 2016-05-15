package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.models.SprConsumer;
import ua.kay.monolit.repositories.ConsumerRepository;

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

    @RequestMapping(value = "/admin/consumer/save", method = RequestMethod.POST)
    public SprConsumer saveConsumer(@RequestBody SprConsumer sprConsumer){
        return consumerRepository.saveAndFlush(sprConsumer);
    }

    @RequestMapping(value = "/admin/consumer/delete/{id}", method = RequestMethod.DELETE)
    public void deleteConsumer(@PathVariable Long id){
        consumerRepository.delete(id);
    }

}
