package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.Attraction;
import com.example.model.Review;

@Repository
public class ReviewDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AttractionDao attractionDao;

    private Review mapRowToReview(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();
        review.setId(rs.getString("id"));
        review.setUserName(rs.getString("userName"));
        review.setComment(rs.getString("comment"));
        review.setGrade(rs.getInt("grade"));
        String attractionId = rs.getString("attractionId");
        Attraction attraction = attractionDao.getAttractionById(attractionId).get(0);
        review.setAttraction(attraction);
        return review;
    }

    public void save(Review review) {
        String sql = "Insert Into reviews (attractionId, userName, comment, grade) Values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, review.getAttraction().getId(), review.getUserName(), review.getComment(), review.getGrade());
    }
}
