package ua.kay.monolit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kay.monolit.models.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
