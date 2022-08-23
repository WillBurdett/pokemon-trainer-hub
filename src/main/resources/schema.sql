
DROP TABLE IF EXISTS pokemon;
DROP TABLE IF EXISTS trainer;

CREATE TABLE IF NOT EXISTS trainer (
  trainer_id int AUTO_INCREMENT NOT NULL,
  name varchar(255) NOT NULL,
  age int NOT NULL,
  gender int NOT NULL,
  PRIMARY KEY (trainer_id)
 );

 CREATE TABLE IF NOT EXISTS pokemon (
  id int AUTO_INCREMENT NOT NULL,
  name varchar(255) NOT NULL,
  level int NOT NULL,
  gender int NOT NULL,
  height double NOT NULL,
  weight double NOT NULL,
  trainer_id int DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_trainer_id
  FOREIGN KEY (trainer_id)
  REFERENCES trainer (trainer_id)
);