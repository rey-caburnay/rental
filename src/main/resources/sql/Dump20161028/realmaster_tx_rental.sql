-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: realmaster
-- ------------------------------------------------------
-- Server version	5.7.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tx_rental`
--

DROP TABLE IF EXISTS `tx_rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tx_rental` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aptId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `dueDate` date DEFAULT NULL,
  `txDate` date DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `deposit` double(9,4) DEFAULT '0.0000',
  `renterId` int(11) NOT NULL,
  `balance` double(9,4) DEFAULT '0.0000',
  `txType` varchar(20) DEFAULT NULL,
  `provider` varchar(50) DEFAULT NULL,
  `amount` double(9,4) DEFAULT '0.0000',
  `status` varchar(20) DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `updatedDate` date DEFAULT NULL,
  `updtCnt` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`,`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_rental`
--

LOCK TABLES `tx_rental` WRITE;
/*!40000 ALTER TABLE `tx_rental` DISABLE KEYS */;
INSERT INTO `tx_rental` VALUES (1,1,1,'2016-09-29','2016-08-01','2016-09-01',NULL,5000.0000,1,0.0000,'1',NULL,5000.0000,'active',1,NULL,0),(2,2,1,'2016-09-29','2016-08-01','2016-09-01',NULL,4000.0000,1,0.0000,'1',NULL,5000.0000,'active',1,NULL,0),(3,1,2,'2016-09-29','2016-08-01','2016-09-01',NULL,3000.0000,1,0.0000,'1',NULL,5000.0000,'active',1,NULL,0),(4,1,3,'2016-09-29','2016-09-29','2016-10-29',NULL,3000.0000,2,0.0000,'1',NULL,3000.0000,'active',1,NULL,0);
/*!40000 ALTER TABLE `tx_rental` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-28 18:09:25
