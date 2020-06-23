-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: caburnay_realmaster
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `mst_apartment`
--

DROP TABLE IF EXISTS `mst_apartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_apartment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `personIncharge` varchar(255) DEFAULT NULL,
  `aptType` varchar(20) DEFAULT NULL,
  `mobileNo` varchar(45) DEFAULT NULL,
  `telNo` varchar(45) DEFAULT NULL,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `electricAccount` varchar(45) DEFAULT NULL,
  `waterAccount` varchar(45) DEFAULT NULL,
  `electricProvider` varchar(45) DEFAULT NULL,
  `mobileIncharge` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_apartment`
--

LOCK TABLES `mst_apartment` WRITE;
/*!40000 ALTER TABLE `mst_apartment` DISABLE KEYS */;
INSERT INTO `mst_apartment` VALUES (1,'Buhisan House 1','evalu','house','2310','234234','Buhisan Cebu City',NULL,'Active','none','none','veco','09199964841'),(2,'Buhisan House 2','evalu','house',NULL,NULL,'Buhisan Cebu City',NULL,'Active','none','none','veco','09199964841'),(3,'Buhisan House 3','evalu','house',NULL,NULL,'Buhisan Cebu City',NULL,'Active','none','none','veco','09199964841'),(4,'Buhisan House 4','evalu','house',NULL,NULL,'Buhisan Cebu City',NULL,'Active','none','none','veco','09199964841'),(5,'Buhisan House 5','evalu','house',NULL,NULL,'Buhisan Cebu City',NULL,'Active','none','none','veco','09199964841'),(6,'Buhisan Bldg 1','evalu','apartment',NULL,NULL,'Buhisan Cebu City',NULL,'Active','none','none','veco','09199964841'),(7,'Buhisan Bldg 2','evalu','aparment',NULL,NULL,'Buhisan Cebu City',NULL,'Active','none','none','veco','09199964841'),(8,'Buhisan Bldg 3','evalu','apartment',NULL,NULL,'Buhisan Cebu City',NULL,'Active','none','none','veco','09199964841'),(9,'Buhisan Bldg 4','evalu','apartment',NULL,NULL,'Buhisan Cebu City',NULL,'Active','none','none','veco','09199964841'),(10,'Indiana Apartment 1','rabel','apartment',NULL,NULL,'Indiana Aero Space ',NULL,'Active','none','none','meco','09199964841'),(11,'India Bldg 2','rabel','apartment',NULL,NULL,'Indiana Aero Space ',NULL,'Active','none','none','meco','09199964841'),(12,'BF Townhomes','ucia',NULL,NULL,NULL,'BLock 5 Lot 18 BF Townhomes ','Abuno Lapulapu','Active','none','none','meco','09199964841');
/*!40000 ALTER TABLE `mst_apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mst_apt_incharge`
--

DROP TABLE IF EXISTS `mst_apt_incharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_apt_incharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `mobileno` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `aptId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_apt_incharge`
--

LOCK TABLES `mst_apt_incharge` WRITE;
/*!40000 ALTER TABLE `mst_apt_incharge` DISABLE KEYS */;
INSERT INTO `mst_apt_incharge` VALUES (1,'John Leo','09199964841',NULL,'active',NULL),(2,'Lucia ','09177204022',NULL,'active',NULL);
/*!40000 ALTER TABLE `mst_apt_incharge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mst_balance`
--

DROP TABLE IF EXISTS `mst_balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `renterId` int(11) DEFAULT NULL,
  `amount` double(9,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_balance`
--

LOCK TABLES `mst_balance` WRITE;
/*!40000 ALTER TABLE `mst_balance` DISABLE KEYS */;
/*!40000 ALTER TABLE `mst_balance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mst_electric`
--

DROP TABLE IF EXISTS `mst_electric`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_electric` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aptId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `accountNo` varchar(50) DEFAULT NULL,
  `currentReading` int(11) DEFAULT '0',
  `previousReading` int(11) DEFAULT '0',
  `provider` varchar(10) DEFAULT NULL,
  `amount` double(9,4) DEFAULT '0.0000',
  `status` varchar(20) DEFAULT NULL,
  `overdue` double(9,4) DEFAULT '0.0000',
  `readingDate` datetime DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `diffReading` int(11) DEFAULT NULL,
  `billingNo` varchar(50) DEFAULT NULL,
  `generationDate` datetime DEFAULT NULL,
  `payment` double(9,4) DEFAULT NULL,
  `meterNo` varchar(50) DEFAULT NULL,
  `lastBillNo` varchar(200) DEFAULT NULL,
  `totalAmount` double(9,4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`,`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_electric`
--

LOCK TABLES `mst_electric` WRITE;
/*!40000 ALTER TABLE `mst_electric` DISABLE KEYS */;
INSERT INTO `mst_electric` VALUES (1,1,1,'none',0,150,'veco',-0.7078,'unpaid',1.7874,NULL,'2018-02-10 00:17:30',20,NULL,'2018-01-11 00:18:32',NULL,'12312312','151560105069911',222.5000),(2,2,2,'none',0,10,'veco',59.3950,'unpaid ',0.0000,'2017-09-07 00:00:00','2017-09-14 21:36:00',5,'150479136016622',NULL,NULL,NULL,'150479136016622',0.0000),(3,6,6,'57394-30000-8',0,30,'veco',113.1525,'unpaid ',0.0000,'2017-07-18 00:00:00','2018-01-13 23:30:23',10,'151525262355066',NULL,NULL,NULL,'151525262355066',113.1500),(4,6,7,'57394-30000-8',0,350,'veco',543.2126,'unpaid ',0.0000,'2017-07-18 00:00:00','2018-01-13 23:38:37',50,'151525311731267',NULL,NULL,NULL,'151525311731267',543.2100),(5,6,8,'57394-30000-8',0,1233,'veco',11939.8047,'unpaid ',0.0000,'2017-09-07 00:00:00','2017-09-14 23:00:09',1110,'150479640921468',NULL,NULL,NULL,'150479640921468',0.0000),(6,3,3,'none',0,0,'veco',0.0000,'unpaid ',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(7,4,4,'none',0,0,'veco',0.0000,'unpaid ',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(8,5,5,'none',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(9,7,9,'67394-30000-7',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(10,7,10,'67394-30000-7',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(11,7,11,'67394-30000-7',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(12,7,12,'67394-30000-7',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(13,7,13,'67394-30000-7',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(14,7,14,'67394-30000-7',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(15,7,15,'67394-30000-7',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(16,7,16,'67394-30000-7',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(17,7,17,'67394-30000-7',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(18,8,18,'13776-30000-9',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(19,8,19,'13776-30000-9',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(20,9,20,'07091- 79900-2',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(21,9,21,'07091- 79900-2',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(22,9,22,'07091- 79900-2',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(23,9,23,'07091- 79900-2',0,0,'veco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(24,10,24,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(25,10,25,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(26,10,26,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(27,10,27,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(28,10,28,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(29,10,29,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(30,10,30,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(31,10,31,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(32,10,32,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(33,10,33,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(34,10,34,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(35,10,35,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(36,10,36,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(37,10,37,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(38,10,38,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(39,10,39,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(40,10,40,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(41,10,41,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(42,10,42,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(43,10,43,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(44,10,44,'none',0,0,'meco',0.0000,'unpaid',0.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL,0.0000),(45,10,45,'none',0,321,'meco',1632.6037,'unpaid',0.0000,'2017-09-07 00:00:00','2017-09-14 22:49:26',198,'15047957660891045',NULL,NULL,NULL,'15047957660891045',0.0000);
/*!40000 ALTER TABLE `mst_electric` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mst_expenses`
--

DROP TABLE IF EXISTS `mst_expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_expenses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aptId` int(11) NOT NULL,
  `descripton` varchar(50) DEFAULT NULL,
  `expType` varchar(50) DEFAULT NULL,
  `amount` double(9,4) DEFAULT '0.0000',
  `status` varchar(20) DEFAULT NULL,
  `roomId` int(11) DEFAULT NULL,
  `currentReading` int(11) DEFAULT NULL,
  `previousReading` int(11) DEFAULT NULL,
  `provider` varchar(45) DEFAULT NULL,
  `previousAmount` double(9,4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_expenses`
--

LOCK TABLES `mst_expenses` WRITE;
/*!40000 ALTER TABLE `mst_expenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `mst_expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mst_renter`
--

DROP TABLE IF EXISTS `mst_renter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_renter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(45) NOT NULL,
  `initial` varchar(5) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobileno` varchar(45) NOT NULL,
  `telno` varchar(45) DEFAULT NULL,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `idPresentend` varchar(100) DEFAULT NULL,
  `emergencyContact` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `registerDate` datetime DEFAULT NULL,
  `aptId` int(11) DEFAULT NULL,
  `roomId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_renter`
--

LOCK TABLES `mst_renter` WRITE;
/*!40000 ALTER TABLE `mst_renter` DISABLE KEYS */;
INSERT INTO `mst_renter` VALUES (1,'botoy','libeth','a','cebu city','09324699448','09324699448',NULL,NULL,'','botoy','active',NULL,NULL,1,1),(9,'Hilda','Hilda',NULL,'cebu city','639430730819','639430730819',NULL,NULL,NULL,'Hilda','active',NULL,NULL,3,3),(10,'Jenny','Jenny',NULL,'buhisan cebu city','639392816236','639392816236',NULL,NULL,NULL,NULL,'active',NULL,NULL,4,4),(11,'Aileen','Aileen',NULL,'buhisan cebu city','639059004867','639059004867',NULL,NULL,NULL,NULL,'active',NULL,NULL,6,6),(12,'Joe','Joe',NULL,'buhisan cebu city','639491989880','639491989880',NULL,NULL,NULL,NULL,'active',NULL,NULL,7,10),(13,'Joseph','Joseph',NULL,'cebu city','639954632071','639954632071',NULL,NULL,NULL,NULL,'active',NULL,NULL,7,11),(14,'Wineva','Wineva',NULL,'cebu','639056261862','639056261862',NULL,NULL,NULL,NULL,'active',NULL,NULL,7,12),(15,'Hankee','Hankee',NULL,'cebu city','none',NULL,NULL,NULL,NULL,NULL,'active',NULL,NULL,7,13),(16,'Merly','Merly',NULL,'cebu city','639756388862','639756388862',NULL,NULL,NULL,NULL,'active',NULL,NULL,7,14),(17,'Jen','Jen',NULL,'cebu city','639423706806','639423706806',NULL,NULL,NULL,NULL,'active',NULL,NULL,7,15),(18,'Margot','Margot',NULL,'cebu city','639425510449','639425510449',NULL,NULL,NULL,NULL,'active',NULL,NULL,9,21),(39,'shinn','caburnay','c',NULL,'09954645794',NULL,'cebu city',NULL,'1231313',NULL,'active',NULL,NULL,2,2);
/*!40000 ALTER TABLE `mst_renter` ENABLE KEYS */;
UNLOCK TABLES;

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
  `roomDesc` varchar(255) DEFAULT NULL,
  `roomType` varchar(20) DEFAULT NULL,
  `size` int(5) DEFAULT '0',
  `occupied` int(5) DEFAULT '0',
  `telNo` varchar(45) DEFAULT NULL,
  `rate` double(9,4) DEFAULT '0.0000',
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_room`
--

LOCK TABLES `mst_room` WRITE;
/*!40000 ALTER TABLE `mst_room` DISABLE KEYS */;
INSERT INTO `mst_room` VALUES (1,1,1,'1','house','house',0,1,NULL,5000.0000,'active'),(2,2,1,'1','house','house',0,1,NULL,3000.0000,'active'),(3,3,1,'1','house','house',0,1,NULL,3000.0000,'active'),(4,4,1,'1','house','house',0,1,NULL,5000.0000,'active'),(5,5,1,'1','house','house',0,1,NULL,50000.0000,'inactive'),(6,6,2,'1','carenderia upper','studio',0,1,NULL,4000.0000,'active'),(7,6,1,'2','carenderia ground 1','studio',0,1,NULL,8000.0000,'active'),(8,6,1,'3','carenderia ground 2','studio',0,1,NULL,4000.0000,'active'),(9,7,1,'1','Bakery Ground','studio',0,1,NULL,6500.0000,'active'),(10,7,1,'2','Bakery D1','standard',0,1,NULL,4000.0000,'active'),(11,7,1,'3','Bakery D2','standard',0,1,NULL,3500.0000,'active'),(12,7,1,'4','Bakery D3','standard',0,1,NULL,4000.0000,'active'),(13,7,1,'5','Bakery D4','standard',0,1,NULL,2500.0000,'active'),(14,7,1,'6','Bakery D5','standard',0,1,NULL,2500.0000,'active'),(15,7,1,'7','Bakery D6','standard ',0,1,NULL,3000.0000,'active'),(16,7,1,'8','Bakery D7','standard',0,1,NULL,5000.0000,'active'),(17,7,1,'9','Bakery D8','standard',0,1,NULL,3500.0000,'active'),(18,8,1,'1','Tindahan Ground','studio',0,1,NULL,6000.0000,'active'),(19,8,2,'2','Tindahan Upper','studio',0,1,NULL,8000.0000,'active'),(20,9,1,'1','Simbahan D1','studio',0,1,NULL,4000.0000,'active'),(21,9,1,'2','Simbahan D2','studio',0,1,NULL,3500.0000,'active'),(22,9,1,'3','Simbahan D3','studio',0,1,NULL,5000.0000,'active'),(23,9,1,'4','Simbahan D4','studio',0,1,NULL,5000.0000,'active'),(24,10,1,'1','R1','standard',0,2,NULL,6000.0000,'active'),(25,10,2,'2','R2','standard',0,2,NULL,6000.0000,'active'),(26,10,2,'3','R3','standard',0,2,NULL,6000.0000,'active'),(27,10,2,'4','R4','standard',0,2,NULL,6000.0000,'active'),(28,10,3,'5','R5','standard',0,2,NULL,6000.0000,'active'),(29,10,3,'6','R6','standard',0,2,NULL,6000.0000,'active'),(30,10,3,'7','R7','standard',0,2,NULL,6000.0000,'active'),(31,10,3,'8','R8','standard',0,2,NULL,6000.0000,'active'),(32,10,4,'9','R9','standard',0,2,NULL,6000.0000,'active'),(33,10,4,'10','R10','standard',0,2,NULL,6000.0000,'active'),(34,10,4,'11','R11','standard',0,2,NULL,6000.0000,'active'),(35,10,1,'1','R12','standard',0,2,NULL,6000.0000,'active'),(36,10,2,'2','R13','standard',0,2,NULL,6000.0000,'active'),(37,10,2,'3','R14','standard',0,2,NULL,6000.0000,'active'),(38,10,2,'4','R15','standard',0,2,NULL,6000.0000,'active'),(39,10,3,'5','R16','standard',0,2,NULL,6000.0000,'active'),(40,10,3,'6','R17','standard',0,2,NULL,6000.0000,'active'),(41,10,3,'7','R18','standard',0,2,NULL,6000.0000,'active'),(42,10,3,'8','R19','standard',0,2,NULL,6000.0000,'active'),(43,10,4,'9','R20','standard',0,2,NULL,6000.0000,'active'),(44,10,4,'10','R21','standard',0,2,NULL,6000.0000,'active'),(45,10,4,'11','R22','standard',0,2,NULL,6000.0000,'active');
/*!40000 ALTER TABLE `mst_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mst_room_type`
--

DROP TABLE IF EXISTS `mst_room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_room_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` int(11) NOT NULL,
  `description` int(5) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_room_type`
--

LOCK TABLES `mst_room_type` WRITE;
/*!40000 ALTER TABLE `mst_room_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `mst_room_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mst_user`
--

DROP TABLE IF EXISTS `mst_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile_no` varchar(45) DEFAULT NULL,
  `tel_no` varchar(45) DEFAULT NULL,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `password` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_user`
--

LOCK TABLES `mst_user` WRITE;
/*!40000 ALTER TABLE `mst_user` DISABLE KEYS */;
INSERT INTO `mst_user` VALUES (1,'Rey','Rey','Rey@email.com','09954645794','2399970','cebu city','Lapulapu City','active',NULL);
/*!40000 ALTER TABLE `mst_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prm_electric`
--

DROP TABLE IF EXISTS `prm_electric`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prm_electric` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provider` varchar(10) NOT NULL,
  `generationCharge` double(9,4) DEFAULT '0.0000',
  `transmissionCharge` double(9,4) DEFAULT '0.0000',
  `systemLoss` double(9,4) DEFAULT '0.0000',
  `supplyCharge` double(9,4) DEFAULT '0.0000',
  `retailCustomer` double(9,4) DEFAULT '0.0000',
  `meteringSystem` double(9,4) DEFAULT '0.0000',
  `lifeLineSubsidy` double(9,4) DEFAULT '0.0000',
  `localFranchiseTax` double(9,4) DEFAULT '0.0000',
  `VAT` double(9,4) DEFAULT '0.0000',
  `generationTax` double(9,4) DEFAULT '0.0000',
  `distributionTax` double(9,4) DEFAULT '0.0000',
  `othersTax` double(9,4) DEFAULT '0.0000',
  `npc` double(9,4) DEFAULT '0.0000',
  `missionaryElectrification` double(9,4) DEFAULT '0.0000',
  `environmentalCharge` double(9,4) DEFAULT '0.0000',
  `fitAllRenewable` double(9,4) DEFAULT '0.0000',
  `surcharge` double(9,4) DEFAULT '0.0000',
  `systemLossTax` double(9,4) DEFAULT NULL,
  `transmissionTax` double(9,4) DEFAULT NULL,
  `distributionCharge` double(9,4) DEFAULT NULL,
  `seniorCitizenSubsidy` double(9,4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`provider`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prm_electric`
--

LOCK TABLES `prm_electric` WRITE;
/*!40000 ALTER TABLE `prm_electric` DISABLE KEYS */;
INSERT INTO `prm_electric` VALUES (1,'veco',5.5448,0.3752,0.8255,0.4118,5.0000,0.6989,0.0980,0.0075,0.0000,0.6188,0.1200,0.1200,0.1938,0.1561,0.0025,0.1240,0.0200,0.0000,0.6188,1.7506,0.0000),(2,'meco',4.9634,0.4162,0.4770,0.2742,5.0000,0.3042,0.0980,0.0075,0.0000,0.6188,0.1200,0.1200,0.1938,0.1561,0.0025,0.1240,0.0200,0.0000,0.6188,0.8293,0.0000);
/*!40000 ALTER TABLE `prm_electric` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prm_electric_new`
--

DROP TABLE IF EXISTS `prm_electric_new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prm_electric_new` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provider` varchar(10) NOT NULL,
  `key` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL,
  `value` double(9,4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`provider`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prm_electric_new`
--

LOCK TABLES `prm_electric_new` WRITE;
/*!40000 ALTER TABLE `prm_electric_new` DISABLE KEYS */;
/*!40000 ALTER TABLE `prm_electric_new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prm_message`
--

DROP TABLE IF EXISTS `prm_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prm_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(45) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `mtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prm_message`
--

LOCK TABLES `prm_message` WRITE;
/*!40000 ALTER TABLE `prm_message` DISABLE KEYS */;
INSERT INTO `prm_message` VALUES (1,'DUE DATE','Please pay your rent for the month of {month} amounting:{amount} to avoid neccessary actions. from-Caburany Apartmentz','2018-01-14 00:00:00',NULL),(2,'BEFORE DUE','This is to inform you that your rental fee(s) will due on {duedate} with total amount of {amount}. Please pay on before due date. from -Caburany Apartmentz','2018-01-14 00:00:00',NULL),(3,'RENT RECEIPT','Thank you for paying your rent with the amount of {amount}. Reference # {reference} from Caburnay Apartmentz','2018-01-14 00:00:00',NULL),(4,'ELECTRIC BILL','Please pay your electric bill for the month of {month} amount: {amount} to avoid disconnection. from Caburnay Apartmentz.','2018-01-14 00:00:00',NULL),(5,'WELCOME','You are now officialy registered as a tenant in caburnay apartmentz. Thank you.','2018-01-14 00:00:00',NULL),(6,'RENT ALERT','Your advise to collect the payment of {apartment} {room} {amount} for this month ({month}).  ','2018-01-14 00:00:00',NULL),(7,'BEFORE DUE ELECTRIC','This is to inform you that your electric bill (s) will due on {duedate} with total amount of {amount}. Please pay on before due date. from -Caburany Apartmentz','2018-01-14 00:00:00',NULL);
/*!40000 ALTER TABLE `prm_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tx_billing`
--

DROP TABLE IF EXISTS `tx_billing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tx_billing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aptId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `currentReading` int(11) DEFAULT '0',
  `previousReading` int(11) DEFAULT '0',
  `diffReading` int(11) DEFAULT '0',
  `provider` varchar(10) DEFAULT NULL,
  `amount` double(15,4) DEFAULT '0.0000',
  `overdue` double(15,4) DEFAULT '0.0000',
  `readingDate` datetime DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `generationDate` datetime DEFAULT NULL,
  `billingNo` varchar(200) DEFAULT NULL,
  `billType` varchar(45) DEFAULT NULL,
  `txDate` datetime DEFAULT NULL,
  `renterId` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `deposit` double(15,4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`,`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_billing`
--

LOCK TABLES `tx_billing` WRITE;
/*!40000 ALTER TABLE `tx_billing` DISABLE KEYS */;
INSERT INTO `tx_billing` VALUES (57,2,2,NULL,NULL,NULL,NULL,3000.0000,0.0000,NULL,'2018-02-01 22:23:54','2018-01-02 22:23:54','151490303418722','property','2018-01-02 22:23:54',39,'active',0.0000),(72,1,1,200,150,50,NULL,543.2126,0.0000,'2018-01-10 00:00:00','2018-02-09 23:36:54','2018-01-10 23:36:54','151559861475411','electric','2018-01-10 23:36:54',NULL,NULL,NULL),(76,1,1,170,150,20,NULL,220.7078,1.7874,'2018-01-11 00:00:00','2018-02-10 00:17:30','2018-01-11 00:17:30','151560105069911','electric','2018-01-11 00:17:30',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tx_billing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tx_collections`
--

DROP TABLE IF EXISTS `tx_collections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tx_collections` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `txId` int(11) NOT NULL,
  `renterId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `aptId` int(11) NOT NULL,
  `amountPaid` double(15,4) DEFAULT '0.0000',
  `balance` double(15,4) DEFAULT '0.0000',
  `deposit` double(15,4) DEFAULT '0.0000',
  `txDate` datetime DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `cashReceived` double(15,4) DEFAULT '0.0000',
  `cashChange` double(15,4) DEFAULT NULL,
  `payment` varchar(45) DEFAULT NULL,
  `currentRoomRate` double(15,4) DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `billingNo` varchar(150) DEFAULT NULL,
  `collectionNo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_collections`
--

LOCK TABLES `tx_collections` WRITE;
/*!40000 ALTER TABLE `tx_collections` DISABLE KEYS */;
INSERT INTO `tx_collections` VALUES (26,19,39,2,2,3000.0000,0.0000,0.0000,'2018-01-03 21:23:56',1,'paid',3000.0000,0.0000,NULL,3000.0000,NULL,NULL,'151498583613922'),(33,1,1,1,1,543.0000,0.0000,0.0000,'2018-01-10 23:44:00',1,'paid',600.0000,57.0000,NULL,NULL,NULL,NULL,'151559904015611'),(34,1,1,1,1,543.0000,0.0000,0.0000,'2018-01-10 23:44:00',1,'paid',600.0000,57.0000,NULL,NULL,NULL,NULL,'151559904015611'),(35,1,1,1,1,545.0000,0.0000,1.7874,'2018-01-11 00:04:30',1,'paid',600.0000,55.0000,NULL,NULL,NULL,NULL,'151560027089811'),(36,1,1,1,1,545.0000,0.0000,1.7874,'2018-01-11 00:04:30',1,'paid',600.0000,55.0000,NULL,NULL,NULL,NULL,'151560027089811'),(37,1,1,1,1,220.0000,1.7874,0.0000,'2018-01-11 00:18:32',1,'paid',220.0000,0.0000,NULL,NULL,NULL,NULL,'151560111195211'),(38,1,1,1,1,220.0000,1.7874,0.0000,'2018-01-11 00:18:32',1,'paid',220.0000,0.0000,NULL,NULL,NULL,NULL,'151560111195211');
/*!40000 ALTER TABLE `tx_collections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tx_collections_details`
--

DROP TABLE IF EXISTS `tx_collections_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tx_collections_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collectionId` int(11) NOT NULL,
  `txId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `aptId` int(11) NOT NULL,
  `amountPaid` double(9,4) DEFAULT '0.0000',
  `balance` double(9,4) DEFAULT '0.0000',
  `deposit` double(9,4) DEFAULT '0.0000',
  `water` double(9,4) DEFAULT '0.0000',
  `electric` double(9,4) DEFAULT '0.0000',
  `txDate` date DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_collections_details`
--

LOCK TABLES `tx_collections_details` WRITE;
/*!40000 ALTER TABLE `tx_collections_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `tx_collections_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tx_electric_collection`
--

DROP TABLE IF EXISTS `tx_electric_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tx_electric_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `billingNo` varchar(200) DEFAULT NULL,
  `amount` double(9,4) DEFAULT '0.0000',
  `status` varchar(20) DEFAULT NULL,
  `overdue` double(9,4) DEFAULT '0.0000',
  `collectionDate` datetime DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `cashReceived` double(9,4) DEFAULT NULL,
  `cashChange` double(9,4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`billingNo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_electric_collection`
--

LOCK TABLES `tx_electric_collection` WRITE;
/*!40000 ALTER TABLE `tx_electric_collection` DISABLE KEYS */;
INSERT INTO `tx_electric_collection` VALUES (1,'150262110370511',420.0000,'paid',0.0000,'2017-08-13 22:48:47','2017-08-20 00:00:00',NULL,NULL),(3,'150262110370511',420.0000,'paid',0.0000,'2017-08-14 21:00:49','2017-08-20 00:00:00',NULL,NULL),(4,'150271679617111',480.0000,'paid',0.0000,'2017-08-14 21:34:21','2017-08-20 18:45:05',NULL,NULL);
/*!40000 ALTER TABLE `tx_electric_collection` ENABLE KEYS */;
UNLOCK TABLES;

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
  `dueDate` datetime DEFAULT NULL,
  `txDate` datetime DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `deposit` double(15,4) DEFAULT '0.0000',
  `renterId` int(11) NOT NULL,
  `balance` double(15,4) DEFAULT '0.0000',
  `txType` varchar(20) DEFAULT NULL,
  `provider` varchar(50) DEFAULT NULL,
  `amount` double(15,4) DEFAULT '0.0000',
  `status` varchar(20) DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `updtCnt` int(11) DEFAULT '0',
  `paymentStatus` varchar(10) DEFAULT NULL,
  `billingNo` varchar(255) DEFAULT NULL,
  `collectionNo` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`,`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_rental`
--

LOCK TABLES `tx_rental` WRITE;
/*!40000 ALTER TABLE `tx_rental` DISABLE KEYS */;
INSERT INTO `tx_rental` VALUES (19,2,2,'2018-02-01 22:23:54','2018-01-02 22:23:54','2018-01-02 22:23:54',NULL,0.0000,39,0.0000,'Cash',NULL,3000.0000,NULL,1,'2018-01-03 21:23:56',1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tx_rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tx_sms`
--

DROP TABLE IF EXISTS `tx_sms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tx_sms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `sender` varchar(15) DEFAULT NULL,
  `recipient` varchar(15) DEFAULT NULL,
  `sendDate` datetime DEFAULT NULL,
  `receivedDate` datetime DEFAULT NULL,
  `deleted` int(1) DEFAULT NULL,
  `requestId` varchar(200) DEFAULT NULL,
  `messageType` varchar(40) DEFAULT NULL,
  `shortcode` varchar(200) DEFAULT NULL,
  `timestamp` varchar(200) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_sms`
--

LOCK TABLES `tx_sms` WRITE;
/*!40000 ALTER TABLE `tx_sms` DISABLE KEYS */;
INSERT INTO `tx_sms` VALUES (23,'Thank you for paying your rent with the amount of PHP 3,000.00. Reference # 151498583613922 from Caburnay Apartmentz',NULL,'09954645794','2018-01-03 21:23:56',NULL,NULL,'36PWXFYRE0FW','SEND','29290332','1514985836610','failed'),(24,'Thank you for paying your rent with the amount of {amount}. Reference # {reference} from Caburnay Apartmentz',NULL,'09324699448','2018-01-07 02:49:14',NULL,NULL,'0I57K9LNV8RG','SEND','29290332','1515264554708','200'),(25,'Thank you for paying your rent with the amount of {amount}. Reference # {reference} from Caburnay Apartmentz',NULL,'09324699448','2018-01-07 18:13:49',NULL,NULL,'4MJ4JOVJ8M6L','SEND','29290332','1515320029187','failed'),(26,'Thank you for paying your rent with the amount of {amount}. Reference # {reference} from Caburnay Apartmentz',NULL,'09324699448','2018-01-10 22:34:54',NULL,NULL,'3LBIR0G08YHM','SEND','29290332','1515594894599','200'),(27,'Thank you for paying your rent with the amount of {amount}. Reference # {reference} from Caburnay Apartmentz',NULL,'09324699448','2018-01-10 23:44:01',NULL,NULL,'ZZK4TMJT8EN6','SEND','29290332','1515599041251','200'),(28,'Thank you for paying your rent with the amount of {amount}. Reference # {reference} from Caburnay Apartmentz',NULL,'09324699448','2018-01-11 00:04:32',NULL,NULL,'118BI49MKPZT','SEND','29290332','1515600272112','200'),(29,'Thank you for paying your rent with the amount of {amount}. Reference # {reference} from Caburnay Apartmentz',NULL,'09324699448','2018-01-11 00:18:33',NULL,NULL,'TO81F6DP1DXJ','SEND','29290332','1515601113234','200');
/*!40000 ALTER TABLE `tx_sms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-21 13:31:36
