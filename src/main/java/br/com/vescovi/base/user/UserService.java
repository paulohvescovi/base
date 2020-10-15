package br.com.vescovi.base.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return User.builder()
                .email("paulo20091994@gmail.com")
                .password("$2a$10$tJr7tCcZGqxm5HNolzFXTuBUFQ5qP9/L0ZrU8t/hpS0DLoQg8e.QG")
                .build();
    }
}
