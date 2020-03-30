/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.10-MariaDB : Database - prosoftjun17
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftjun17` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `prosoftjun17`;

/*Table structure for table `oprema` */

DROP TABLE IF EXISTS `oprema`;

CREATE TABLE `oprema` (
  `OpremaID` int(11) NOT NULL,
  `Naziv` varchar(255) DEFAULT NULL,
  `CenaPoDanu` double DEFAULT NULL,
  `ProizvodjacID` int(11) DEFAULT NULL,
  PRIMARY KEY (`OpremaID`),
  KEY `ProizvodjacID` (`ProizvodjacID`),
  CONSTRAINT `oprema_ibfk_1` FOREIGN KEY (`ProizvodjacID`) REFERENCES `proizvodjac` (`ProizvodjacID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oprema` */

insert  into `oprema`(`OpremaID`,`Naziv`,`CenaPoDanu`,`ProizvodjacID`) values 
(1,'Jakna',300,1),
(2,'Kapa',100,2),
(3,'Duks',200,1),
(4,'Pantalone',400,3),
(5,'Naocare UVEX Downhill',300,4),
(6,'Cipele',500,5),
(7,'Skije',600,6);

/*Table structure for table `proizvodjac` */

DROP TABLE IF EXISTS `proizvodjac`;

CREATE TABLE `proizvodjac` (
  `ProizvodjacID` int(11) NOT NULL,
  `Naziv` varchar(255) DEFAULT NULL,
  `Adresa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ProizvodjacID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `proizvodjac` */

insert  into `proizvodjac`(`ProizvodjacID`,`Naziv`,`Adresa`) values 
(1,'Dainese','Adresa1'),
(2,'Savoy','Adresa2'),
(3,'Kjus','Adresa3'),
(4,'Uxex','Adresa4'),
(5,'Dalbello','Adresa5'),
(6,'Rossignol','Adresa6');

/*Table structure for table `rentiranje` */

DROP TABLE IF EXISTS `rentiranje`;

CREATE TABLE `rentiranje` (
  `RentiranjeID` int(11) NOT NULL,
  `Klijent` varchar(255) DEFAULT NULL,
  `DatumOd` date DEFAULT NULL,
  `DatumDo` date DEFAULT NULL,
  `UkupanIznos` double DEFAULT NULL,
  PRIMARY KEY (`RentiranjeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rentiranje` */

insert  into `rentiranje`(`RentiranjeID`,`Klijent`,`DatumOd`,`DatumDo`,`UkupanIznos`) values 
(1,'klijent 1','2020-01-06','2020-01-13',8400),
(2,'klijent 2','2020-02-02','2020-02-06',2400),
(3,'klijent 3','2020-01-01','2020-01-03',1800),
(4,'klijent 3','2020-02-02','2020-02-05',300);

/*Table structure for table `stavkarentiranja` */

DROP TABLE IF EXISTS `stavkarentiranja`;

CREATE TABLE `stavkarentiranja` (
  `RentiranjeID` int(11) NOT NULL,
  `RB` int(11) NOT NULL,
  `OpremaID` int(11) DEFAULT NULL,
  `Iznos` double DEFAULT NULL,
  PRIMARY KEY (`RentiranjeID`,`RB`),
  KEY `OpremaID` (`OpremaID`),
  CONSTRAINT `stavkarentiranja_ibfk_1` FOREIGN KEY (`RentiranjeID`) REFERENCES `rentiranje` (`RentiranjeID`),
  CONSTRAINT `stavkarentiranja_ibfk_2` FOREIGN KEY (`OpremaID`) REFERENCES `oprema` (`OpremaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stavkarentiranja` */

insert  into `stavkarentiranja`(`RentiranjeID`,`RB`,`OpremaID`,`Iznos`) values 
(1,1,1,2100),
(1,2,5,2100),
(1,3,7,4200),
(2,1,1,1200),
(2,2,2,400),
(2,3,3,800),
(3,1,1,600),
(3,2,7,1200),
(4,1,2,300);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
