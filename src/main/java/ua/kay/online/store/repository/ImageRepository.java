package ua.kay.online.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kay.online.store.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
