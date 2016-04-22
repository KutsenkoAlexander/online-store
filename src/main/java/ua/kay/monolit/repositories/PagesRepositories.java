package ua.kay.monolit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.models.Pages;

public interface PagesRepositories extends JpaRepository<Pages, Long> {
    @Async
    Pages findByUrl(String url);

    @Async
    Pages findById(Long id);
}
