package ua.kay.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kay.monolith.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByIdAccount(Long id);
    Account findByName(String name);
}
