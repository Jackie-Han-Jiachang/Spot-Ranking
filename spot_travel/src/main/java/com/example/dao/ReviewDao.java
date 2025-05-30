package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.Attraction;
import com.example.model.Review;
import com.example.model.User;

@Repository
public class ReviewDao {
    @Autowired 
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AttractionDao attractionDao;
    @Autowired
    private UserDao userDao;

    // map the database table to the review
    private Review mapToReview(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();
        review.setId(rs.getString("id"));
        review.setComment(rs.getString("comment"));
        review.setGrade(rs.getInt("grade"));
        String attractionId = rs.getString("attractionId");
        Attraction attraction = attractionDao.getAttractionById(attractionId).get(0);
        review.setAttraction(attraction);

        String userId = rs.getString("userId");
        User user = userDao.getUserById(userId).get(0);
        review.setUser(user);
        return review;
    }

    // User type in comments and grades for the attraction, and the information is
    // stored in the database
    public void save(Review review) {
        String sql = "Insert Into reviews (attractionId, userId, comment, grade) Values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, review.getAttraction().getId(), review.getUser().getId(), review.getComment(), review.getGrade());
    }

    // get all reviews from the database and present them in the webpage
    public List<Review> getReviewsById(String attractionId) {
        String sql = "Select * From reviews Where attractionId = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapToReview(rs, rowNum), attractionId);
    }

    // delete the review from the database after pressing the delete button
    public void deleteReview(String id) {
        String sql = "Delete From reviews Where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
