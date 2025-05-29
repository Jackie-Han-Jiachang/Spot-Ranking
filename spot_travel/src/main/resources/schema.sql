-- Initiate empty table
-- source: https://forcedotcom.github.io/phoenix/
-- https://www.w3schools.com/sql/default.asp
CREATE TABLE IF NOT EXISTS attractions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS reviews(
    id INT AUTO_INCREMENT PRIMARY KEY,
    attractionId INT NOT NULL,
    userId VARCHAR(255) NOT NULL,
    comment TEXT NOT NULL,
    grade INT NOT NULL,
    FOREIGN KEY (attractionId) REFERENCES attractions(id),
    FOREIGN KEY (userId) REFERENCES users(id)
);

