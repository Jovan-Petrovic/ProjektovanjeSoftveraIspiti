/*
SQLyog Ultimate v9.50 
MySQL - 5.6.20 : Database - prosoftnov17
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftnov17` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `prosoftnov17`;

/*Table structure for table `investicija` */

DROP TABLE IF EXISTS `investicija`;

CREATE TABLE `investicija` (
  `InvesticijaID` int(11) NOT NULL AUTO_INCREMENT,
  `InvestitorID` int(11) DEFAULT NULL,
  `KompanijaID` int(11) DEFAULT NULL,
  `Datum` date DEFAULT NULL,
  `Iznos` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`InvesticijaID`),
  KEY `InvestitorID` (`InvestitorID`),
  KEY `KompanijaID` (`KompanijaID`),
  CONSTRAINT `investicija_ibfk_1` FOREIGN KEY (`InvestitorID`) REFERENCES `investitor` (`InvestitorID`),
  CONSTRAINT `investicija_ibfk_2` FOREIGN KEY (`KompanijaID`) REFERENCES `kompanija` (`KompanijaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `investicija` */

/*Table structure for table `investitor` */

DROP TABLE IF EXISTS `investitor`;

CREATE TABLE `investitor` (
  `InvestitorID` int(11) NOT NULL,
  `Naziv` varchar(255) DEFAULT NULL,
  `Adresa` varchar(255) DEFAULT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`InvestitorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `investitor` */

insert  into `investitor`(`InvestitorID`,`Naziv`,`Adresa`,`Username`,`Password`) values (1,'Venture Capital 1','Adresa 1','vc1','vc1'),(2,'VCFON','Jove Ilica 154','fon','fon'),(3,'HITEC VC','Adresa 2','hitec','hitec');

/*Table structure for table `kompanija` */

DROP TABLE IF EXISTS `kompanija`;

CREATE TABLE `kompanija` (
  `KompanijaID` int(11) NOT NULL,
  `Naziv` varchar(255) DEFAULT NULL,
  `OblastPoslovanja` varchar(255) DEFAULT NULL,
  `IPO` tinyint(1) DEFAULT NULL,
  `Tip` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`KompanijaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `kompanija` */

insert  into `kompanija`(`KompanijaID`,`Naziv`,`OblastPoslovanja`,`IPO`,`Tip`) values (1,'Google','Tech',1,'A'),(2,'Uber','Tech',0,'A'),(3,'Tesla','Car',1,'A'),(4,'Atom','Car',0,'A'),(5,'Beti','Lifestyle',0,'A');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
