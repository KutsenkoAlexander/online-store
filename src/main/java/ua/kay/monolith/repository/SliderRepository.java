package ua.kay.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolith.model.Slider;

import java.util.List;

public interface SliderRepository extends JpaRepository<Slider, Integer> {

    @Async
    @Query("select s from Slider s order by s.image")
    List<Slider> findAllOrderByImage();

}
