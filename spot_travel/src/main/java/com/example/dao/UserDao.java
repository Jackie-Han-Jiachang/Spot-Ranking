package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User mapToUser(ResultSet ret, int rowNum) throws SQLException {
    User user = new User();
    user.setId(ret.getString("id"));
    user.setUsername(ret.getString("username"));
    user.setPassword(ret.getString("password"));
    return user;
}

    public void saveUser(User user) {
        String sql = "Insert Into users (username, password) Values (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }

    public String validityTest(User user){
        String userName = user.getUsername();
        String password = user.getPassword();
        String sql = "Select id From users Where username = ? And password = ?";
        return jdbcTemplate.queryForObject(sql, String.class, userName, password);
    }

    public List<User> getUserById(String id) {
        String sql = "Select * From users Where id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapToUser(rs, rowNum), id);
    }
}
