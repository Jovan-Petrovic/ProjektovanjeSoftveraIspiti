/*
SQLyog Ultimate v9.50 
MySQL - 5.6.20 : Database - prosoftfeb17
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftfeb17` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `prosoftfeb17`;

/*Table structure for table `kursnalista` */

DROP TABLE IF EXISTS `kursnalista`;

CREATE TABLE `kursnalista` (
  `KursnaListaID` int(11) NOT NULL AUTO_INCREMENT,
  `Dan` datetime DEFAULT NULL,
  `Izvor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KursnaListaID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `kursnalista` */

insert  into `kursnalista`(`KursnaListaID`,`Dan`,`Izvor`) values (1,'2016-12-09 00:00:00','NBS'),(2,'2016-12-21 00:00:00','NBS'),(3,'2017-02-03 00:00:00','NBS');

/*Table structure for table `stavkakursneliste` */

DROP TABLE IF EXISTS `stavkakursneliste`;

CREATE TABLE `stavkakursneliste` (
  `KursnaListaID` int(11) NOT NULL,
  `RB` int(11) NOT NULL,
  `ZemljaID` int(11) DEFAULT NULL,
  `VaziZa` int(11) DEFAULT NULL,
  `Kupovni` double DEFAULT NULL,
  `Prodajni` double DEFAULT NULL,
  `Srednji` double DEFAULT NULL,
  PRIMARY KEY (`KursnaListaID`,`RB`),
  KEY `ZemljaID` (`ZemljaID`),
  CONSTRAINT `stavkakursneliste_ibfk_1` FOREIGN KEY (`KursnaListaID`) REFERENCES `kursnalista` (`KursnaListaID`),
  CONSTRAINT `stavkakursneliste_ibfk_2` FOREIGN KEY (`ZemljaID`) REFERENCES `zemlja` (`ZemljaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stavkakursneliste` */

insert  into `stavkakursneliste`(`KursnaListaID`,`RB`,`ZemljaID`,`VaziZa`,`Kupovni`,`Prodajni`,`Srednji`) values (1,1,1,1,122.9955,123.7357,123.3656),(1,2,2,1,114.202,114.8892,114.5456),(1,3,3,1,113.3808,114.0632,113.722),(1,4,4,1,144.3609,145.2297,144.7953),(2,1,1,1,122.9956,123.7358,123.3657),(2,2,2,1,114.202,114.8892,114.5456),(2,3,3,1,113.3808,114.0632,113.722),(2,4,4,1,144.3609,145.2297,144.7953),(3,1,1,1,123.6427,124.3867,124.0147),(3,2,2,1,114.9202,115.6118,115.266),(3,3,3,1,115.6945,116.3907,116.0426),(3,4,4,1,143.8876,144.7536,144.3206);

/*Table structure for table `zemlja` */

DROP TABLE IF EXISTS `zemlja`;

CREATE TABLE `zemlja` (
  `ZemljaID` int(11) NOT NULL,
  `Naziv` varchar(255) DEFAULT NULL,
  `Valuta` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ZemljaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `zemlja` */

insert  into `zemlja`(`ZemljaID`,`Naziv`,`Valuta`) values (1,'EMU','EUR'),(2,'SAD','USD'),(3,'Švajcarska','CHF'),(4,'Velika Britanija','GBP');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
