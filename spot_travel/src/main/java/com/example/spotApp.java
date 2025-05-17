package com.example;

import java.sql.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class spotApp {
    // public static void main(String[] args) {
    //     try {
    //         // Step 1: Connect to an in-memory H2 database
    //         Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");

    //         // Step 2: Create table
    //         Statement stmt = conn.createStatement();
    //         stmt.execute("CREATE TABLE spots(id INT PRIMARY KEY, name VARCHAR(255))");

    //         // Step 3: Insert data
    //         stmt.execute("INSERT INTO spots VALUES(1, 'Tokyo'), (2, 'Paris'), (3, 'New York')");

    //         // Step 4: Query data
    //         ResultSet rs = stmt.executeQuery("SELECT * FROM spots");

    //         // Step 5: Print results
    //         while (rs.next()) {
    //             System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
    //         }

    //         conn.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

     public static void main(String[] args) {
       SpringApplication.run(spotApp.class, args);
    }
}