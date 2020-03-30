/*
SQLyog Ultimate v9.50 
MySQL - 5.6.20 : Database - prosoftmart19
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftmart19` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `prosoftmart19`;

/*Table structure for table `advokat` */

DROP TABLE IF EXISTS `advokat`;

CREATE TABLE `advokat` (
  `AdvokatID` int(11) NOT NULL,
  `Ime` varchar(255) DEFAULT NULL,
  `Prezime` varchar(255) DEFAULT NULL,
  `SpecijalnostZaVrstuPostupka` int(11) DEFAULT NULL,
  PRIMARY KEY (`AdvokatID`),
  KEY `SpecijalnostZaVrstuPostupka` (`SpecijalnostZaVrstuPostupka`),
  CONSTRAINT `advokat_ibfk_1` FOREIGN KEY (`SpecijalnostZaVrstuPostupka`) REFERENCES `vrstapostuka` (`VrstaPostukaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `advokat` */

insert  into `advokat`(`AdvokatID`,`Ime`,`Prezime`,`SpecijalnostZaVrstuPostupka`) values (1,'Ivana','Ivanovic',1),(2,'Borivoje','Boric',2),(3,'Dragana','Draganic',1);

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `KlijentID` int(11) NOT NULL,
  `Ime` varchar(255) DEFAULT NULL,
  `Prezime` varchar(255) DEFAULT NULL,
  `Telefon` varchar(255) DEFAULT NULL,
  `ElPosta` varchar(255) DEFAULT NULL,
  `Adresa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KlijentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `klijent` */

insert  into `klijent`(`KlijentID`,`Ime`,`Prezime`,`Telefon`,`ElPosta`,`Adresa`) values (1,'Marko','Markovic','0113950800','test1@fon.rs','Adresa 1'),(2,'Jovana','Jovanic','0113950801','test2@fon.rs','Adresa 2');

/*Table structure for table `predmet` */

DROP TABLE IF EXISTS `predmet`;

CREATE TABLE `predmet` (
  `PredmetID` int(11) NOT NULL,
  `Naziv` varchar(255) DEFAULT NULL,
  `Problem` varchar(1000) DEFAULT NULL,
  `Datum` datetime DEFAULT NULL,
  `AdvokatID` int(11) DEFAULT NULL,
  `KlijentID` int(11) DEFAULT NULL,
  `VrstaPostupkaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PredmetID`),
  KEY `AdvokatID` (`AdvokatID`),
  KEY `KlijentID` (`KlijentID`),
  KEY `VrstaPostupkaID` (`VrstaPostupkaID`),
  CONSTRAINT `predmet_ibfk_1` FOREIGN KEY (`AdvokatID`) REFERENCES `advokat` (`AdvokatID`),
  CONSTRAINT `predmet_ibfk_2` FOREIGN KEY (`KlijentID`) REFERENCES `klijent` (`KlijentID`),
  CONSTRAINT `predmet_ibfk_3` FOREIGN KEY (`VrstaPostupkaID`) REFERENCES `vrstapostuka` (`VrstaPostukaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `predmet` */

/*Table structure for table `vrstapostuka` */

DROP TABLE IF EXISTS `vrstapostuka`;

CREATE TABLE `vrstapostuka` (
  `VrstaPostukaID` int(11) NOT NULL,
  `Naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`VrstaPostukaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vrstapostuka` */

insert  into `vrstapostuka`(`VrstaPostukaID`,`Naziv`) values (1,'Parnicni postupak'),(2,'Vanparnicni postupak'),(3,'Izvrsni postupak'),(4,'Upravni spor'),(5,'Krivicni postupak'),(6,'Prekrsajni postupak');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
