package ua.kay.monolit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.models.ImgCategory;

import java.util.List;

public interface ImgCategoryRepository extends JpaRepository<ImgCategory, Long> {

}
