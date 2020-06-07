package ua.kay.online.store.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kay.online.store.model.Consumer;
import ua.kay.online.store.repository.ConsumerRepository;

import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@RestController
public class ConsumerController {

    private final ConsumerRepository consumerRepository;

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
