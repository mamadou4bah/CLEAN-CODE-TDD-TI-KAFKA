CREATE DATABASE IF NOT EXISTS siPmuDb;

USE `siPmuDb`;

DROP TABLE IF EXISTS partant;
DROP TABLE IF EXISTS course;

-- TABLE : course
CREATE TABLE IF NOT EXISTS course (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date_course DATE NOT NULL,
    name VARCHAR(255) NOT NULL,
    number INT NOT NULL,
    CONSTRAINT uq_course_date_num UNIQUE (date_course, number)
);

-- TABLE : partant
CREATE TABLE IF NOT EXISTS partant (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    number INT NOT NULL,
    course_id BIGINT NOT NULL,
    CONSTRAINT fk_partant_course FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    CONSTRAINT uq_partant_course_num UNIQUE (course_id, number)
);
