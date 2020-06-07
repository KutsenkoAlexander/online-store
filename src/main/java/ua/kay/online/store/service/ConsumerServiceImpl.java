package ua.kay.online.store.service;

import org.springframework.stereotype.Service;
import ua.kay.online.store.exception.ObjectNotFoundException;
import ua.kay.online.store.model.Consumer;
import ua.kay.online.store.repository.ConsumerRepository;

import java.util.stream.Stream;

@Service
public class ConsumerServiceImpl implements CrudService<Consumer> {

    private ConsumerRepository consumerRepository;

    public ConsumerServiceImpl(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @Override
    public Stream<Consumer> findAll() { return consumerRepository.findAll().stream(); }

    @Override
    public Consumer findById(Long id) throws ObjectNotFoundException {
        return consumerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Consumer was not found by id", id));
    }

    @Override
    public Consumer save(Consumer consumer) { return consumerRepository.save(consumer); }

    @Override
    public void delete(Consumer consumer) { consumerRepository.delete(consumer); }

    @Override
    public void deleteById(Long id) { consumerRepository.deleteById(id); }
}
