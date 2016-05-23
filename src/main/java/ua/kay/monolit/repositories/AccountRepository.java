package ua.kay.monolit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kay.monolit.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByIdAccount(Long id);
    Account findByName(String name);
}
