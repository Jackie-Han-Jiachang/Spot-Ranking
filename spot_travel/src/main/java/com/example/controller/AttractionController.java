package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.AttractionDao;
import com.example.model.Attraction;

@RestController
@RequestMapping("/api")
public class AttractionController {
    @Autowired
    private AttractionDao attractionDao;

    @GetMapping("/attractions")
    public List<Attraction> getAllAttractions() {
        return attractionDao.getAllAttractions();
    }

    @PostMapping("/attractions")
    public void saveAttraction(@RequestBody Attraction attraction) {
        attractionDao.saveAttraction(attraction);
    }
}
