CREATE DATABASE IF NOT EXISTS ied_projet
CHARACTER SET utf8 COLLATE utf8_bin;

USE ied_projet;

CREATE TABLE film (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	titre VARCHAR(255) NOT NULL,
	date_sortie DATE,
	genre VARCHAR(50),
	distributeur VARCHAR(255),
	budget DOUBLE,
	revenus_etats_unis DOUBLE,
	revenus_mondiaux DOUBLE
)ENGINE=InnoDB,
CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE talend_info (
	`key` VARCHAR(255) NOT NULL UNIQUE,
	`value` VARCHAR(255) NOT NULL
)ENGINE=InnoDB,
CHARACTER SET utf8 COLLATE utf8_bin



INSERT INTO talend_info VALUES 
('CSV_FILEPATH', 'C:\\Users\\Anne-Sophie\\Desktop\\Cours M1\\IED - Intégration et Entrepôts de Données\\Projet\\'),
('CSV_FILENAME', 'movieBudgets.csv'),
('CSV_GENERATED_FILENAME', 'movies_.*\\.csv'),
('DB_HOST', 'localhost'),
('DB_PORT', '3306'),
('DB_USER', 'root'),
('DB_PASSWORD', ''),
('DB_DATABASE', 'ied_projet'),
('DB_FILM_TABLE', 'film');