package com.example.model;

public class Review {
    private String id;
    private Attraction attraction;
    private String userName;
    private String comment;
    private int grade;

    public Review() {
    }

    public Review(String id, Attraction attractionId, String userName, String comment, int grade) {
        this.id = id;
        this.attraction = attractionId;
        this.userName = userName;
        this.comment = comment;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    
}
