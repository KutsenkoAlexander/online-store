package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolit.models.Pages;
import ua.kay.monolit.models.SprConsumer;
import ua.kay.monolit.repositories.ConsumerRepository;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository consumerRepository;

    @RequestMapping("/all")
    public List<SprConsumer> findAllConsumers(){
        return consumerRepository.findAll();
    }

    @RequestMapping("/{id}")
    public List<SprConsumer> getProductConsumers(@PathVariable("id") Integer id) {
        return consumerRepository.findConsumerByProductCategoryId(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public SprConsumer saveConsumer(@RequestBody SprConsumer sprConsumer){
        return consumerRepository.saveAndFlush(sprConsumer);
    }

}
