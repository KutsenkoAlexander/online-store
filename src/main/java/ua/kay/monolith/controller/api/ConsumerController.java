package ua.kay.monolith.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.monolith.model.Consumer;
import ua.kay.monolith.repository.ConsumerRepository;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class ConsumerController {

    private ConsumerRepository consumerRepository;

    public ConsumerController(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @RequestMapping("/consumer/all")
    public List<Consumer> findAllConsumers(){
        return consumerRepository.findAll();
    }

    @RequestMapping("/consumer/{id}")
    public Stream<Consumer> getProductConsumers(@PathVariable("id") Long id) {
        return consumerRepository.findConsumerByProductCategoryId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/consumer/save", method = RequestMethod.POST)
    public Consumer saveConsumer(@RequestBody Consumer consumer){
        return consumerRepository.saveAndFlush(consumer);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/rest/consumer/delete/{id}", method = RequestMethod.DELETE)
    public void deleteConsumer(@PathVariable Long id){
        consumerRepository.deleteById(id);
    }
}
