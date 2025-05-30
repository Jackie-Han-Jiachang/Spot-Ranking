package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.AttractionDao;
import com.example.dao.ReviewDao;
import com.example.dao.UserDao;
import com.example.model.Attraction;
import com.example.model.Review;
import com.example.model.User;

@RestController
@RequestMapping("/api")
public class AttractionController {
    @Autowired
    private AttractionDao attractionDao;
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private UserDao userDao;

    // get all attractions from the database and present them in the webpage
    @GetMapping("/attractions")
    public List<Attraction> getAllAttractions() {
        return attractionDao.getAllAttractions();
    }

    // get attraction information typed in by the user and stored in the database
    @PostMapping("/attractions")
    public void saveAttraction(@RequestBody Attraction attraction) {
        attractionDao.saveAttraction(attraction);
    }

    // delete the attraction from the database
    @DeleteMapping("/attractions/{id}")
    public void deleteAttraction(@PathVariable("id") String id) {
        attractionDao.deleteAttraction(id);
    }

    // get the attraction information by its id so that it can be related to the reviews
    @GetMapping("/attractions/{id}")
    public List<Attraction> getAttractionById(@PathVariable("id") String id) {
        return attractionDao.getAttractionById(id);
    }

    // get information about the reviews of the attraction from the webpage and
    // store it into the database
    @PostMapping("/reviews")
    public Review addReview(@RequestBody Review review) {
        reviewDao.save(review);
        return review;
    }

    // get all reviews of the attraction from the database and present them in the
    // webpage
    @GetMapping("/reviews/{id}")
    public List<Review> getReviewsById(@PathVariable("id") String id) {
        return reviewDao.getReviewsById(id);
    }

    // delete the review from the database
    @DeleteMapping("/reviews/{id}")
    public void deleteReview(@PathVariable("id") String id) {
        reviewDao.deleteReview(id);
    }

    // register a new user by saving the user information typed in by the user into
    // the database
    @PostMapping("/users")
    public void saveUser(@RequestBody User user) {
        userDao.saveUser(user);
    }

    // after typing in the username and password, check if the user is valid by checking the database
    // only with userId the user can post reviews
    @PostMapping("/users/verify")
    public String validityTest(@RequestBody User user) {
        String userId = userDao.validityTest(user);
        if (userId == null) {
            return "Invalid username or password";
        }
        return userId;
    }

}
