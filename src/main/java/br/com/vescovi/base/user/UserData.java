package br.com.vescovi.base.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserData extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
