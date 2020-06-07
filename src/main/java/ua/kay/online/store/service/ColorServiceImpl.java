package ua.kay.online.store.service;

import org.springframework.stereotype.Service;
import ua.kay.online.store.exception.ObjectNotFoundException;
import ua.kay.online.store.model.Color;
import ua.kay.online.store.repository.ColorRepository;

import java.util.stream.Stream;

@Service
public class ColorServiceImpl implements CrudService<Color> {

    private ColorRepository colorRepository;

    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public Stream<Color> findAll() {
        return colorRepository.findAll().stream();
    }

    @Override
    public Color findById(Long id) throws ObjectNotFoundException {
        return colorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Color not found by id", id));
    }

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public void delete(Color color) {
        colorRepository.delete(color);
    }

    @Override
    public void deleteById(Long id) {
        colorRepository.deleteById(id);
    }
}