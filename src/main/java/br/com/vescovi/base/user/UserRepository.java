package br.com.vescovi.base.user;

import java.util.List;

//example using JDBC
public interface UserRepository {

    public int count();
    public int save(User user);
    public User findById(Long id);
    public int update(User user);
    public int deleteById(Long id);
    public List<User> findAllByGmail();

}
