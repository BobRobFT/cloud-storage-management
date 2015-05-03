SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `db12407404` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `db12407404` ;

-- -----------------------------------------------------
-- Table `db12407404`.`account_002`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db12407404`.`account_002` ;

CREATE TABLE IF NOT EXISTS `db12407404`.`account_002` (
  `user_ID` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `occupation` VARCHAR(45) NOT NULL,
  `salt` VARCHAR(200) NOT NULL,
  `IV` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`user_ID`),
  UNIQUE INDEX `ID_UNIQUE` (`user_ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db12407404`.`link`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db12407404`.`link` ;

CREATE TABLE IF NOT EXISTS `db12407404`.`link` (
  `account_002_user_ID` INT NOT NULL,
  `url` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`account_002_user_ID`),
  CONSTRAINT `fk_link_account_002`
    FOREIGN KEY (`account_002_user_ID`)
    REFERENCES `db12407404`.`account_002` (`user_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db12407404`.`course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db12407404`.`course` ;

CREATE TABLE IF NOT EXISTS `db12407404`.`course` (
  `course_ID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `account_002_user_ID` INT NOT NULL,
  PRIMARY KEY (`course_ID`),
  UNIQUE INDEX `course_ID_UNIQUE` (`course_ID` ASC),
  INDEX `fk_course_account_0021_idx` (`account_002_user_ID` ASC),
  CONSTRAINT `fk_course_account_0021`
    FOREIGN KEY (`account_002_user_ID`)
    REFERENCES `db12407404`.`account_002` (`user_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db12407404`.`course_has_account_002`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db12407404`.`course_has_account_002` ;

CREATE TABLE IF NOT EXISTS `db12407404`.`course_has_account_002` (
  `course_course_ID` INT NOT NULL,
  `account_002_user_ID` INT NOT NULL,
  PRIMARY KEY (`course_course_ID`, `account_002_user_ID`),
  INDEX `fk_course_has_account_002_account_0021_idx` (`account_002_user_ID` ASC),
  INDEX `fk_course_has_account_002_course1_idx` (`course_course_ID` ASC),
  CONSTRAINT `fk_course_has_account_002_course1`
    FOREIGN KEY (`course_course_ID`)
    REFERENCES `db12407404`.`course` (`course_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_has_account_002_account_0021`
    FOREIGN KEY (`account_002_user_ID`)
    REFERENCES `db12407404`.`account_002` (`user_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
