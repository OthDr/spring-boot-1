package com.example.demo1.database;

import com.example.demo1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsersDataAccess {

    @Autowired
    private JdbcTemplate sql;

    public int insertUser(User user) {
        return sql.update("INSERT INTO `users` (`id`, `firstname`, `lastname`) VALUES (null, ?, ?)",
                new Object[]{user.getFirstname(), user.getLastname()}
        );
    }

    public List<User> getAllUsers() {
        return sql.query("SELECT * FROM `users`", new UserRowMapper());
    }

    public int updateUser(User user) {
        return sql.update(
                "UPDATE users SET firstname = ?, lastname = ? WHERE id = ?",
                user.getFirstname(),
                user.getLastname(),
                user.getId()
        );
    }

    public int deleteUser(int id) {
        return sql.update("DELETE FROM `users` WHERE `id`=?", id);
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getInt("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname")
            );
        }
    }
}
