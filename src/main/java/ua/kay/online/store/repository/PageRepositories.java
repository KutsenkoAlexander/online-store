package ua.kay.online.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kay.online.store.model.Page;

import java.util.Optional;

@Repository
public interface PageRepositories extends JpaRepository<Page, Long> {
    Optional<Page> findByUrl(String url);
}
