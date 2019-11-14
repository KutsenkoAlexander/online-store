package ua.kay.monolith.service;

import org.springframework.stereotype.Service;
import ua.kay.monolith.repository.ColorRepository;

@Service
public class ColorService {
    private ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public void delete(Integer id) {
        colorRepository.deleteByIdSprColors(id);
    }
}
