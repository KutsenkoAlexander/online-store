package ua.kay.monolith.service;

import org.springframework.stereotype.Service;
import ua.kay.monolith.repository.SizeRepository;

@Service
public class SizeService {

    private SizeRepository sizeRepository;

    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public void delete(Long id) {
        sizeRepository.deleteById(id);
    }
}
