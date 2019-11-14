package ua.kay.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kay.monolith.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
