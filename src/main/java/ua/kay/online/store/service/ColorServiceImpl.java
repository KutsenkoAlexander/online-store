package ua.kay.online.store.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.kay.online.store.exception.ObjectNotFoundException;
import ua.kay.online.store.model.Color;
import ua.kay.online.store.repository.ColorRepository;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ColorServiceImpl implements CrudService<Color> {

    private final ColorRepository colorRepository;

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color findById(Long id) throws ObjectNotFoundException {
        return colorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Color not found by id", id));
    }

    @Override
    public Color save(Color color) {
        return colorRepository.saveAndFlush(color);
    }

    @Override
    public void delete(Color color) {
        colorRepository.delete(color);
    }

    @Override
    public void deleteById(Long id) {
        colorRepository.deleteById(id);
    }

    public Set<Color> findColorsByProductCategoryId(Long id) {
        return colorRepository.findColorsByProductCategoryId(id);
    }
}
