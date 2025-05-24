-- Initiate empty table
-- source: https://forcedotcom.github.io/phoenix/
CREATE TABLE attractions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);

CREATE TABLE reviews(
    id INT AUTO_INCREMENT PRIMARY KEY,
    attraction_id INT NOT NULL,
    username VARCHAR(255) NOT NULL,
    comment TEXT NOT NULL,
    rating INT NOT NULL,
    FOREIGN KEY (attraction_id) REFERENCES attractions(id)
);