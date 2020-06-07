package ua.kay.online.store.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.online.store.model.Consumer;
import ua.kay.online.store.repository.ConsumerRepository;

import java.util.List;

@AllArgsConstructor
@RestController
public class ConsumerController {

    private final ConsumerRepository consumerRepository;

    @RequestMapping("/consumer/all")
    public List<Consumer> findAllConsumers(){
        return consumerRepository.findAll();
    }

    @RequestMapping("/consumer/{id}")
    public List<Consumer> getProductConsumers(@PathVariable("id") Long id) {
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
