package com.example.dao;

import com.example.model.Attraction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// hard code sql statements to query the database
// source: https://forcedotcom.github.io/phoenix/
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
// based on Spring Boot

@Repository
public class AttractionDao {

    @Autowired // so that i do not need to create a constructor
    private JdbcTemplate jdbcTemplate;

    // map the database table to the attraction
    public Attraction mapToAttraction(ResultSet ret, int rowNum) throws SQLException {
        Attraction attraction = new Attraction();
        attraction.setId(ret.getString("id"));
        attraction.setName(ret.getString("name"));
        attraction.setLocation(ret.getString("location"));
        return attraction;
    }

    // save attraction to the database
    public void saveAttraction(Attraction attraction) {
        // when the attraction is new
        if (attraction.getId() == null) {
            String sql = "Insert Into attractions (name, location) Values (?, ?)";
            jdbcTemplate.update(sql, attraction.getName(), attraction.getLocation());
        } else {
            // when the attraction is already in the database
            String sql = "Update attractions Set name = ?, location = ? Where id = ?";
            jdbcTemplate.update(sql, attraction.getName(), attraction.getLocation(), attraction.getId());
        }
    }

    public List<Attraction> getAllAttractions() {
        String sql = "Select * From attractions";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapToAttraction(rs, rowNum));
    }

    public void deleteAttraction(String id) {
        String sql = "Delete From attractions Where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Attraction> getAttractionById(String id) {
        String sql = "Select * From attractions Where id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapToAttraction(rs, rowNum), id);
    }

}
