package ua.kay.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolith.model.Pages;

import java.util.Optional;

public interface PagesRepositories extends JpaRepository<Pages, Long> {
    @Async
    Pages findByUrl(String url);

    @Async
    Optional<Pages> findById(Long id);
}
