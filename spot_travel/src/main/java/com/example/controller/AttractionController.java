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
import com.example.model.Attraction;
import com.example.model.Review;

@RestController
@RequestMapping("/api")
public class AttractionController {
    @Autowired
    private AttractionDao attractionDao;
    @Autowired
    private ReviewDao reviewDao;

    @GetMapping("/attractions")
    public List<Attraction> getAllAttractions() {
        return attractionDao.getAllAttractions();
    }

    @PostMapping("/attractions")
    public void saveAttraction(@RequestBody Attraction attraction) {
        attractionDao.saveAttraction(attraction);
    }

    @DeleteMapping("/attractions/{id}")
    public void deleteAttraction(@PathVariable("id") String id) {
        attractionDao.deleteAttraction(id);
    }

    @GetMapping("/attractions/{id}")
    public List<Attraction> getAttractionById(@PathVariable("id") String id) {
        return attractionDao.getAttractionById(id);
    }

    @PostMapping("/reviews")
    public Review addReview(@RequestBody Review review) {
        reviewDao.save(review);
        return review;
    }

    @GetMapping("/reviews/{id}")
    public List<Review> getReviewsById(@PathVariable("id") String id) {
        return reviewDao.getReviewsById(id);
    }

    @DeleteMapping("/reviews/{id}")
    public void deleteReview(@PathVariable("id") String id) {
        reviewDao.deleteReview(id);
    }
}
