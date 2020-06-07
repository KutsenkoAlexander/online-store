package ua.kay.online.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.kay.online.store.model.Slider;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SliderRepository extends JpaRepository<Slider, Long> {

    @Query("select s from Slider s order by s.image")
    List<Slider> findAllOrderByImage();
}
