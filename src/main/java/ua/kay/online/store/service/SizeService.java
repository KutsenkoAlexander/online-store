package ua.kay.online.store.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.kay.online.store.model.Size;
import ua.kay.online.store.repository.SizeRepository;

import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class SizeService {

    private final SizeRepository sizeRepository;

    public void delete(Long id) {
        sizeRepository.deleteById(id);
    }

    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    public List<Size> findSizesByProductCategoryId(Long id) {
        return sizeRepository.findSizesByProductCategoryId(id);
    }

    public Size save(Size size) {
        return sizeRepository.saveAndFlush(size);
    }
}
