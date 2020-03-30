/*
SQLyog Ultimate v9.50 
MySQL - 5.6.20 : Database - prosoftjan19
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftjan19` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `prosoftjan19`;

/*Table structure for table `kviz` */

DROP TABLE IF EXISTS `kviz`;

CREATE TABLE `kviz` (
  `KvizID` int(11) NOT NULL,
  `Naziv` varchar(255) DEFAULT NULL,
  `DatumVremePocetka` datetime DEFAULT NULL,
  `DatumVremeKraja` datetime DEFAULT NULL,
  `Pobednik` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KvizID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `kviz` */

insert  into `kviz`(`KvizID`,`Naziv`,`DatumVremePocetka`,`DatumVremeKraja`,`Pobednik`) values (1,'Kviz1',NULL,NULL,NULL),(2,'Kviz2',NULL,NULL,NULL),(3,'Kviz3',NULL,NULL,NULL),(4,'Kviz4',NULL,NULL,NULL);

/*Table structure for table `pitanje` */

DROP TABLE IF EXISTS `pitanje`;

CREATE TABLE `pitanje` (
  `PitanjeID` int(11) NOT NULL,
  `Pitanje` varchar(255) DEFAULT NULL,
  `TacanOdgovor` varchar(255) DEFAULT NULL,
  `BrojPoena` int(11) DEFAULT NULL,
  `KvizID` int(11) NOT NULL,
  PRIMARY KEY (`PitanjeID`),
  KEY `KvizID` (`KvizID`),
  CONSTRAINT `pitanje_ibfk_1` FOREIGN KEY (`KvizID`) REFERENCES `kviz` (`KvizID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pitanje` */

insert  into `pitanje`(`PitanjeID`,`Pitanje`,`TacanOdgovor`,`BrojPoena`,`KvizID`) values (1,'Pitanje kviz1p1?','k1p1',5,1),(2,'Pitanje kviz1p2?','k1p2',4,1),(3,'Pitanje kviz1p3?','k1p3',5,1),(4,'Pitanje kviz1p4?','k1p4',6,1),(5,'Pitanje kviz1p5?','k1p5',5,1),(6,'Pitanje kviz1p6?','k1p6',5,1),(7,'Pitanje kviz1p7?','k1p7',5,1),(8,'Pitanje kviz1p8?','k1p8',5,1),(9,'Pitanje kviz1p9?','k1p9',5,1),(10,'Pitanje kviz1p10?','k1p10',5,1),(11,'Pitanje kviz2p1?','k2p1',5,2),(12,'Pitanje kviz2p2?','k2p2',4,2),(13,'Pitanje kviz2p3?','k2p3',5,2),(14,'Pitanje kviz2p4?','k2p4',6,2),(15,'Pitanje kviz2p5?','k2p5',5,2),(16,'Pitanje kviz2p6?','k2p6',5,2),(17,'Pitanje kviz2p7?','k2p7',5,2),(18,'Pitanje kviz2p8?','k2p8',5,2),(19,'Pitanje kviz2p9?','k2p9',5,2),(20,'Pitanje kviz2p10?','k2p10',5,2),(21,'Pitanje kviz3p1?','k3p1',5,3),(22,'Pitanje kviz3p2?','k3p2',5,3),(23,'Pitanje kviz3p3?','k3p3',4,3),(24,'Pitanje kviz3p4?','k3p4',5,3),(25,'Pitanje kviz3p5?','k3p5',7,3),(26,'Pitanje kviz3p6?','k3p6',5,3),(27,'Pitanje kviz3p7?','k3p7',4,3),(28,'Pitanje kviz3p8?','k3p8',6,3),(29,'Pitanje kviz3p9?','k3p9',7,3),(30,'Pitanje kviz3p10?','k3p10',5,3),(31,'Pitanje kviz4p1?','k4p1',5,4),(32,'Pitanje kviz4p2?','k4p2',4,4),(33,'Pitanje kviz4p3?','k4p3',6,4),(34,'Pitanje kviz4p4?','k4p4',3,4),(35,'Pitanje kviz4p5?','k4p5',5,4),(36,'Pitanje kviz4p6?','k4p6',4,4),(37,'Pitanje kviz4p7?','k4p7',7,4),(38,'Pitanje kviz4p8?','k4p8',8,4),(39,'Pitanje kviz4p9?','k4p9',5,4),(40,'Pitanje kviz4p10?','k4p10',4,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
