package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// source: https://spring.io/guides/gs/spring-boot

@SpringBootApplication
public class SpotApp {
  public static void main(String[] args) throws SQLException {
    // Initialize the H2 database connection from application
    Connection conn = DriverManager.getConnection("jdbc:h2:file:./data/attractionDB", "sa", "");
    // run the Spring Boot application
    SpringApplication.run(SpotApp.class, args);
    conn.close();
  }
}