package ua.kay.monolit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.models.SprConsumer;
import ua.kay.monolit.repositories.ConsumerRepository;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository consumerRepository;

    @RequestMapping("/{id}")
    public List<SprConsumer> getProductConsumers(@PathVariable("id") Integer id) {
        return consumerRepository.findConsumerByProductCategoryId(id);
    }

}
