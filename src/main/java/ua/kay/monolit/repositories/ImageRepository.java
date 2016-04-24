package ua.kay.monolit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kay.monolit.models.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
