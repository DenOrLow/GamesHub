CREATE TABLE IF NOT EXISTS publication (
                                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                     title VARCHAR(255) NOT NULL,
                                     content LONGTEXT NOT NULL,
                                     created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS videogame (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         title VARCHAR(255) NOT NULL,
                         description LONGTEXT NOT NULL
);