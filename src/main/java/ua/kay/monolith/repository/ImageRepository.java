package ua.kay.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kay.monolith.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
