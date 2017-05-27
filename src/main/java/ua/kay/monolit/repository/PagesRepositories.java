package ua.kay.monolit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import ua.kay.monolit.model.Pages;

public interface PagesRepositories extends JpaRepository<Pages, Long> {
    @Async
    Pages findByUrl(String url);

    @Async
    Pages findById(Long id);
}
