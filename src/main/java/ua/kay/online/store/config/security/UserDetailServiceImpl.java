package ua.kay.online.store.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.kay.online.store.model.Account;
import ua.kay.online.store.repository.AccountRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository service;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Account account = service.findByName(name).orElseThrow(() -> new UsernameNotFoundException("no user found with " + name));
        return new AccountUserDetails(account);
    }

}
