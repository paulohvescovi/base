package br.com.vescovi.base.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserData extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("select u from User u where u.email = :email")
    List<User> findByEmailJPA(@Param("email") String email);

    @Modifying
    @Query("UPDATE User SET password = :password WHERE id = :id")
    boolean updatePassword(@Param("id") Long id, @Param("password") String password);
}

