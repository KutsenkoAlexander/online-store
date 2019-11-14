package ua.kay.monolith.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.monolith.repository.SizeRepository;

@Service
public class SizeService {

    private SizeRepository sizeRepository;

    @Autowired
    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public void delete(Integer id) {
        sizeRepository.deleteById(id);
    }
}
