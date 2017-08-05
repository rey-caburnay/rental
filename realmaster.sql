-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: realmaster
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_apartment`
--

LOCK TABLES `mst_apartment` WRITE;
/*!40000 ALTER TABLE `mst_apartment` DISABLE KEYS */;
INSERT INTO `mst_apartment` VALUES (1,'buhisan','paul','apartment','234234234','234234','labangon',NULL,'Active','1234456777','123123','veco'),(2,'Lapulapu','Paul','apartment',NULL,NULL,NULL,NULL,'Active','232123232','1231233','meco'),(3,'Shrie','Paule','store',NULL,NULL,NULL,NULL,'Active','123123123','123123123','veco');
/*!40000 ALTER TABLE `mst_apartment` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`,`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_electric`
--

LOCK TABLES `mst_electric` WRITE;
/*!40000 ALTER TABLE `mst_electric` DISABLE KEYS */;
INSERT INTO `mst_electric` VALUES (1,1,1,'12345',32757,32719,'meco',414.1946,'unpaid',0.0000,'2017-07-18 00:00:00','2017-08-20 00:00:00',38,'150159383607611','2017-07-18 00:00:00',NULL,'12312312','150159383607611'),(2,2,1,'12314124',9795,9240,'veco',0.0000,'unpaid ',7145.0000,'2017-07-18 00:00:00','2017-07-18 00:00:00',0,NULL,NULL,NULL,NULL,NULL);
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
  `lastname` varchar(45) DEFAULT NULL,
  `initial` varchar(5) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobileno` varchar(45) DEFAULT NULL,
  `telno` varchar(45) DEFAULT NULL,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `idPresentend` varchar(100) DEFAULT NULL,
  `emergencyContact` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `registerDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_renter`
--

LOCK TABLES `mst_renter` WRITE;
/*!40000 ALTER TABLE `mst_renter` DISABLE KEYS */;
INSERT INTO `mst_renter` VALUES (1,'Rey','Caburnay','C','rey@emaol.com','09954645794','2399292','Cebu','Cebut','SSS_ID','000000','active',NULL,NULL),(2,'Bryan','Rey','C','br@email.com','09954645794','12312','Cebu','Cebu','Drivers License','00000','active',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_room`
--

LOCK TABLES `mst_room` WRITE;
/*!40000 ALTER TABLE `mst_room` DISABLE KEYS */;
INSERT INTO `mst_room` VALUES (1,1,1,'1',NULL,'standard',1,0,NULL,5000.0000,'active'),(2,1,1,'2',NULL,'standard',1,0,NULL,5000.0000,'active'),(3,1,2,'3',NULL,'double',2,0,NULL,5000.0000,'active'),(4,1,2,'4',NULL,'double',1,0,NULL,3000.0000,'active'),(5,2,1,'1',NULL,'house',1,0,NULL,2000.0000,'active');
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_user`
--

LOCK TABLES `mst_user` WRITE;
/*!40000 ALTER TABLE `mst_user` DISABLE KEYS */;
INSERT INTO `mst_user` VALUES (1,'Rey','Rey','Rey@email.com','09954645794','2399970','cebu city','Lapulapu City','active');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prm_electric_new`
--

LOCK TABLES `prm_electric_new` WRITE;
/*!40000 ALTER TABLE `prm_electric_new` DISABLE KEYS */;
/*!40000 ALTER TABLE `prm_electric_new` ENABLE KEYS */;
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
  `amount` double(9,4) DEFAULT '0.0000',
  `overdue` double(9,4) DEFAULT '0.0000',
  `readingDate` datetime DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `generationDate` datetime DEFAULT NULL,
  `billingNo` varchar(200) DEFAULT NULL,
  `billType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`,`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_billing`
--

LOCK TABLES `tx_billing` WRITE;
/*!40000 ALTER TABLE `tx_billing` DISABLE KEYS */;
INSERT INTO `tx_billing` VALUES (1,1,1,32757,32719,NULL,NULL,414.1946,0.0000,NULL,'2017-08-20 00:00:00','2017-07-31 22:34:11','150151165150711','electric'),(7,1,1,32757,32719,NULL,NULL,414.1946,0.0000,'2017-08-01 00:00:00','2017-08-20 00:00:00','2017-08-01 21:12:40','150159316193411','electric'),(12,1,1,32757,32719,NULL,NULL,414.1946,0.0000,'2017-08-01 00:00:00','2017-08-20 00:00:00','2017-08-01 21:23:56','150159383607611','electric');
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
  `amountPaid` double(9,4) DEFAULT '0.0000',
  `balance` double(9,4) DEFAULT '0.0000',
  `deposit` double(9,4) DEFAULT '0.0000',
  `txDate` datetime DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `cashReceived` double(9,4) DEFAULT NULL,
  `cashChange` double(9,4) DEFAULT NULL,
  `payment` varchar(45) DEFAULT NULL,
  `currentRoomRate` double(9,4) DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_collections`
--

LOCK TABLES `tx_collections` WRITE;
/*!40000 ALTER TABLE `tx_collections` DISABLE KEYS */;
INSERT INTO `tx_collections` VALUES (1,1,1,1,1,50000.0000,0.0000,8000.0000,'2017-07-12 00:00:00',1,NULL,50000.0000,0.0000,NULL,NULL,NULL),(2,1,1,2,1,50000.0000,0.0000,8000.0000,'2017-07-12 22:30:43',1,NULL,50000.0000,0.0000,NULL,NULL,NULL);
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
-- Table structure for table `tx_electrill_collection`
--

DROP TABLE IF EXISTS `tx_electrill_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tx_electrill_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `billingNo` int(11) DEFAULT NULL,
  `amount` double(9,4) DEFAULT '0.0000',
  `status` varchar(20) DEFAULT NULL,
  `overdue` double(9,4) DEFAULT '0.0000',
  `collectionDate` datetime DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`billingNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_electrill_collection`
--

LOCK TABLES `tx_electrill_collection` WRITE;
/*!40000 ALTER TABLE `tx_electrill_collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `tx_electrill_collection` ENABLE KEYS */;
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
  `deposit` double(9,4) DEFAULT '0.0000',
  `renterId` int(11) NOT NULL,
  `balance` double(9,4) DEFAULT '0.0000',
  `txType` varchar(20) DEFAULT NULL,
  `provider` varchar(50) DEFAULT NULL,
  `amount` double(9,4) DEFAULT '0.0000',
  `status` varchar(20) DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `updtCnt` int(11) DEFAULT '0',
  `paymentStatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`,`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_rental`
--

LOCK TABLES `tx_rental` WRITE;
/*!40000 ALTER TABLE `tx_rental` DISABLE KEYS */;
INSERT INTO `tx_rental` VALUES (1,1,1,NULL,'2017-07-12 21:39:08','2016-09-01 08:00:00',NULL,0.0000,1,5000.0000,'cash',NULL,30000.0000,'partial',1,'2017-07-12 21:39:08',0,'unpaid'),(2,2,1,'2016-09-29 00:00:00','2016-08-01 00:00:00','2016-09-01 00:00:00',NULL,4000.0000,1,0.0000,'1',NULL,5000.0000,'active',1,NULL,0,'unpaid'),(3,1,2,'2017-07-26 00:00:00','2017-07-12 22:30:43','2016-09-01 00:00:00',NULL,8000.0000,1,0.0000,'cash',NULL,5000.0000,'active',1,'2017-07-12 22:30:43',0,NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_sms`
--

LOCK TABLES `tx_sms` WRITE;
/*!40000 ALTER TABLE `tx_sms` DISABLE KEYS */;
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

-- Dump completed on 2017-08-05 12:43:39
