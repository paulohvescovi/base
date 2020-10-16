package br.com.vescovi.base.user;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired EntityManager entityManager;

    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public int save(User user) {
        return jdbcTemplate.update(
                "insert into users (email, password) values(?,?)",
                user.getEmail(), user.getPassword());
    }

    @Override
    public User findById(Long id) {

//        String sql = "select * from users where id = :id";
//
//        Query query = entityManager.createNativeQuery(sql);
//        query.setParameter("id", id);
//        query.unwrap(SQLQuery.class).addEntity(User.class);
//
//        List resultList = query.getResultList();

        return jdbcTemplate.queryForObject(
                "select * from users where id = ?",
                new Object[]{id},
                User.class
        );
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update(
                "update users set email = ?, password = ? where id = ?",
                user.getEmail(), user.getPassword(), user.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete users where id = ?", id
        );
    }

    @Override
    public List<User> findAllByGmail() {
        return jdbcTemplate.query(
                "select * from users where email like ? and id > ?",
                new Object[]{"%" + "@gmail.com" + "%", 1},
                (rs, rowNum) ->
                        User.builder()
                            .id(rs.getLong("id"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .build()
        );
    }
}
