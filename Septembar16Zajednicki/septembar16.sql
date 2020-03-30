/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.11-MariaDB : Database - septembar16
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`septembar16` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `septembar16`;

/*Table structure for table `lek` */

DROP TABLE IF EXISTS `lek`;

CREATE TABLE `lek` (
  `lekID` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `lista` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`lekID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `lek` */

insert  into `lek`(`lekID`,`naziv`,`cena`,`lista`) values 
(1,'lek1',256.3,'a'),
(2,'lek2',250,'b'),
(3,'lek3',244.2,'b'),
(4,'lek4',302,'c');

/*Table structure for table `lekar` */

DROP TABLE IF EXISTS `lekar`;

CREATE TABLE `lekar` (
  `lekarID` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `specijalnost` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`lekarID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `lekar` */

insert  into `lekar`(`lekarID`,`ime`,`prezime`,`specijalnost`) values 
(1,'Petar','Petrovic','spec1'),
(2,'Milan','Milanovic','spec2'),
(3,'Jovan','Jovanovic','spec3'),
(4,'Mico','Micovic','spec4');

/*Table structure for table `osiguranolice` */

DROP TABLE IF EXISTS `osiguranolice`;

CREATE TABLE `osiguranolice` (
  `osiguranoLiceID` int(11) NOT NULL AUTO_INCREMENT,
  `lbo` int(11) DEFAULT NULL,
  `ime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `osnovOsiguranja` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`osiguranoLiceID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `osiguranolice` */

insert  into `osiguranolice`(`osiguranoLiceID`,`lbo`,`ime`,`prezime`,`osnovOsiguranja`) values 
(1,2563,'Rade','Radovic','osnov1'),
(2,2365,'Zoran','Zoric','osnov2'),
(3,4521,'Marija','Maric','osnov3'),
(4,5456,'Pera','Peric','osnov4');

/*Table structure for table `recept` */

DROP TABLE IF EXISTS `recept`;

CREATE TABLE `recept` (
  `receptID` int(11) NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `ukupanIznos` double DEFAULT NULL,
  `lekarID` int(11) DEFAULT NULL,
  `osiguranoLiceID` int(11) DEFAULT NULL,
  `lekID` int(11) DEFAULT NULL,
  PRIMARY KEY (`receptID`),
  KEY `lekarID` (`lekarID`),
  KEY `osiguranoLiceID` (`osiguranoLiceID`),
  KEY `lekID` (`lekID`),
  CONSTRAINT `recept_ibfk_1` FOREIGN KEY (`lekarID`) REFERENCES `lekar` (`lekarID`),
  CONSTRAINT `recept_ibfk_2` FOREIGN KEY (`osiguranoLiceID`) REFERENCES `osiguranolice` (`osiguranoLiceID`),
  CONSTRAINT `recept_ibfk_3` FOREIGN KEY (`lekID`) REFERENCES `lek` (`lekID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `recept` */

insert  into `recept`(`receptID`,`datum`,`kolicina`,`ukupanIznos`,`lekarID`,`osiguranoLiceID`,`lekID`) values 
(1,'2020-01-06',5,890.75,1,1,1),
(2,'2020-01-06',2,148.84,3,3,3),
(3,'2020-01-05',3,568,2,2,3),
(4,'2020-01-05',4,256.3,1,3,4),
(5,'2020-01-06',3,555.3,2,3,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
