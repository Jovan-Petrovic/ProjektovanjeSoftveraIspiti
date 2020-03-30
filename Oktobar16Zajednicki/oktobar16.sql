/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.11-MariaDB : Database - oktobar2016
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oktobar2016` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `oktobar2016`;

/*Table structure for table `fakultet` */

DROP TABLE IF EXISTS `fakultet`;

CREATE TABLE `fakultet` (
  `fakultetID` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresa` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `telefon` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`fakultetID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `fakultet` */

insert  into `fakultet`(`fakultetID`,`naziv`,`adresa`,`telefon`) values 
(1,'FON','Jove Ilica 154','069 525636'),
(3,'ETF','Bulevar Kralja Aleksandra 52','067 5826589');

/*Table structure for table `kandidat` */

DROP TABLE IF EXISTS `kandidat`;

CREATE TABLE `kandidat` (
  `kandidatID` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `korisnickoIme` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lozinka` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`kandidatID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `kandidat` */

insert  into `kandidat`(`kandidatID`,`ime`,`prezime`,`korisnickoIme`,`lozinka`) values 
(1,'Jovan','Petrovic','j','j'),
(2,'Stevan','Radovanovic','s','s');

/*Table structure for table `prijava` */

DROP TABLE IF EXISTS `prijava`;

CREATE TABLE `prijava` (
  `prijavaID` int(11) NOT NULL AUTO_INCREMENT,
  `godinaUpisa` int(11) NOT NULL,
  `godinaDiplomiranja` int(11) NOT NULL,
  `prosecnaOcena` double NOT NULL,
  `datumPrijave` date NOT NULL,
  `oslobodjenPrijemnog` tinyint(1) NOT NULL,
  `fakultetID` int(11) NOT NULL,
  `studijskiProgramID` int(11) NOT NULL,
  `kandidatID` int(11) NOT NULL,
  PRIMARY KEY (`prijavaID`),
  KEY `fakultetID` (`fakultetID`),
  KEY `studijskiProgramID` (`studijskiProgramID`),
  KEY `kandidatID` (`kandidatID`),
  KEY `fakultetID_2` (`fakultetID`),
  KEY `studijskiProgramID_2` (`studijskiProgramID`),
  KEY `kandidatID_2` (`kandidatID`),
  CONSTRAINT `prijava_ibfk_1` FOREIGN KEY (`fakultetID`) REFERENCES `fakultet` (`fakultetID`),
  CONSTRAINT `prijava_ibfk_2` FOREIGN KEY (`kandidatID`) REFERENCES `kandidat` (`kandidatID`),
  CONSTRAINT `prijava_ibfk_3` FOREIGN KEY (`studijskiProgramID`) REFERENCES `studijskiprogram` (`studijskiProgramID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `prijava` */

insert  into `prijava`(`prijavaID`,`godinaUpisa`,`godinaDiplomiranja`,`prosecnaOcena`,`datumPrijave`,`oslobodjenPrijemnog`,`fakultetID`,`studijskiProgramID`,`kandidatID`) values 
(5,2000,2004,9.3,'2020-01-05',1,1,1,1),
(6,2006,2011,8.8,'2020-01-05',0,1,2,2),
(7,2008,2012,8.3,'2020-01-05',1,1,1,1),
(8,2005,2010,8,'2020-01-05',0,1,1,2),
(9,2006,2010,7.8,'2020-01-05',0,1,2,2);

/*Table structure for table `studijskiprogram` */

DROP TABLE IF EXISTS `studijskiprogram`;

CREATE TABLE `studijskiprogram` (
  `studijskiProgramID` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`studijskiProgramID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `studijskiprogram` */

insert  into `studijskiprogram`(`studijskiProgramID`,`naziv`) values 
(1,'ISIT'),
(2,'Menadzment');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
