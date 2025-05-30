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

    // map the database table to the user
    public User mapToUser(ResultSet ret, int rowNum) throws SQLException {
    User user = new User();
    user.setId(ret.getString("id"));
    user.setUsername(ret.getString("username"));
    user.setPassword(ret.getString("password"));
    return user;
}
    // save user to the database when they register their information
    public void saveUser(User user) {
        String sql = "Insert Into users (username, password) Values (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }
    // check if the user is already registered in the database. Otherwise they cannnot access to the webpage
    public String validityTest(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String sql = "Select id From users Where username = ? And password = ?";
        return jdbcTemplate.queryForObject(sql, String.class, username, password);
    }

    // get all users from the database, so that the name can be presented along with the reviews
    public List<User> getUserById(String id) {
        String sql = "Select * From users Where id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapToUser(rs, rowNum), id);
    }
}
