DROP DATABASE IF EXISTS ibm;
CREATE DATABASE ibm;
USE ibm;

DROP TABLE IF EXISTS providers;

CREATE TABLE providers (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) UNIQUE NOT NULL,
                           registration_date DATE,
                           client_id INT
) ENGINE=INNODB;

INSERT INTO providers VALUES (1, "Coca-cola", "2023-04-22", 5);
INSERT INTO providers VALUES (2, "Pepsi", "2023-04-22", 5);
INSERT INTO providers VALUES (3, "Redbull", "2023-04-22", 6);

DROP DATABASE IF EXISTS test;
CREATE DATABASE test;
USE test;

DROP TABLE IF EXISTS providers;

CREATE TABLE providers (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) UNIQUE NOT NULL,
                           registration_date DATE,
                           client_id INT
) ENGINE=INNODB;

INSERT INTO providers VALUES (1, "Coca-cola", "2023-04-22", 5);
INSERT INTO providers VALUES (2, "Pepsi", "2023-04-22", 5);
INSERT INTO providers VALUES (3, "Redbull", "2023-04-22", 6);