package ua.kay.monolit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kay.monolit.models.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsernameAndPassword(String username, String password);
}
