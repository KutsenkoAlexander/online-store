package ua.kay.monolit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kay.monolit.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
