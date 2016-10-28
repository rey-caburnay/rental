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
-- Table structure for table `mst_room`
--

DROP TABLE IF EXISTS `mst_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aptId` int(11) NOT NULL,
  `floor` int(5) DEFAULT NULL,
  `roomNo` varchar(255) DEFAULT NULL,
  `roomType` varchar(20) DEFAULT NULL,
  `size` int(5) DEFAULT '0',
  `occupied` int(5) DEFAULT '0',
  `telNo` varchar(45) DEFAULT NULL,
  `rate` double(9,4) DEFAULT '0.0000',
  `status` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_room`
--

LOCK TABLES `mst_room` WRITE;
/*!40000 ALTER TABLE `mst_room` DISABLE KEYS */;
INSERT INTO `mst_room` VALUES (1,1,1,'1','standard',1,0,NULL,3000.0000,'active','1 queen size bed, aircon, with own cr, hot n cold shower.'),(2,1,1,'2','standard',1,0,NULL,3000.0000,'active','1 queen size bed, aircon, with own cr, hot n cold shower.'),(3,1,2,'1','double',2,0,NULL,3500.0000,'active','1 queen size bed, aircon, with own cr, hot n cold shower.'),(4,1,2,'2','double',1,0,NULL,3500.0000,'active','1 queen size bed, aircon, with own cr, hot n cold shower.'),(5,2,1,'1','house',1,0,NULL,5000.0000,'active','1 queen size bed, aircon, with own cr, hot n cold shower.');
/*!40000 ALTER TABLE `mst_room` ENABLE KEYS */;
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
