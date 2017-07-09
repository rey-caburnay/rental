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


/**** apartment ***/
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


/**** rooms ***/
CREATE TABLE `mst_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aptId` int(11) NOT NULL,
  `floor` int(5) DEFAULT NULL,
  `roomNo` varchar(255) DEFAULT NULL,
  `roomDesc` varchar(255) DEFAULT NULL,
  `roomType` varchar(20) DEFAULT NULL,
  `size` int(5) DEFAULT 0,
  `occupied` int(5) DEFAULT 0,
  `telNo` varchar(45) DEFAULT NULL,
  `rate` double(9,4) DEFAULT 0,
  `status`varchar(20) DEFAULT NULL,
  `shared` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/**** room_type***/
CREATE TABLE `mst_room_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` int(11) NOT NULL,
  `description` int(5) DEFAULT NULL,
  `status`varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/**** expenses ***/
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

/**** electric ***/
CREATE TABLE `mst_electric` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aptId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `descripton` varchar(50) DEFAULT NULL,
  `curReading` int(11) DEFAULT 0,
  `prevReading` int(11) DEFAULT 0,
  `provider` varchar(10) DEFAULT NULL,
  `amount` double(9,4) DEFAULT 0.0000,
  `status` varchar(20) DEFAULT NULL,
  `overdue` double(9,4) DEFAULT 0.0000,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`,`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/** param for electric ***/
CREATE TABLE `prm_electric` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provider` varchar (10) NOT NULL,
  `generationCharge` double(9,4) DEFAULT 0.0000,
  `transmissionCharge` double(9,4) DEFAULT 0.0000,
  `systemLoss` double(9,4) DEFAULT 0.0000,
  `supplyDistribution` double(9,4) DEFAULT 0.0000,
  `retailCustomer` double(9,4) DEFAULT 0.0000,
  `meteringSystem` double(9,4) DEFAULT 0.0000,
  `lifeLineSubsidy` double(9,4) DEFAULT 0.0000,
  `localFranchiseTax` double(9,4) DEFAULT 0.0000,
  `VAT` double(9,4) DEFAULT 0.0000,
  `generalTransmission` double(9,4) DEFAULT 0.0000,
  `distribution` double(9,4) DEFAULT 0.0000,
  `others` double(9,4) DEFAULT 0.0000,
  `npc` double(9,4) DEFAULT 0.0000,
  `missionaryElectrification` double(9,4) DEFAULT 0.0000,
  `environmentalCharge` double(9,4) DEFAULT 0.0000,
  `fitAllRenewable` double(9,4) DEFAULT 0.0000,
  `surcharge` double(9,4) DEFAULT 0.0000,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`provider`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/**** renter ****/
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


/******************** transaction schema *********************/
CREATE TABLE `tx_rental` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aptId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `dueDate` date DEFAULT NULL,
  `txDate` date default NULL,
  `startDate` date default null,
  `endDate` date null default null,
  `deposit` double(9,4) default 0.0000,
  `renterId` int(11) NOT NULL,
  `balance` double(9,4) default 0.0000,
  `txType` varchar(20) DEFAULT NULL,
  `provider` varchar(50) DEFAULT NULL,
  `amount` double(9,4) DEFAULT 0.000,
  `paymentStatus` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `userId` int(11) not null,
   `updatedDate` date default NULL,
   `updtCnt` int(11) default 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`,`roomId`)
  )ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
  
  /************* collections ***********/
CREATE TABLE `tx_collections` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `txId` int(11) NOT NULL,
  `renterId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `aptId` int(11) NOT NULL,
  `amountPaid` double(9,4) DEFAULT '0.0000',
  `balance` double(9,4) DEFAULT '0.0000',
  `deposit` double(9,4) DEFAULT '0.0000',
  `txDate` date DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `cashReceived` double(9,4) DEFAULT NULL,
  `cashChange` double(9,4) DEFAULT NULL,
  `payment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tx_collections_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collectionId` int(11) NOT NULL,
  `txId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `aptId` int(11) NOT NULL,
  `amountPaid`  double(9,4) default 0.0000,
  `balance`  double(9,4) default 0.0000,
  `deposit` double(9,4) default 0.0000,
  `water` double(9,4) default 0.0000,
  `electric` double(9,4) default 0.0000,
  `txDate` date default NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/********************** tx_sms ******************/
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

/*********************** mst_balance ********************/
CREATE TABLE `mst_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `renterId` int(11) DEFAULT NULL,
  `amount` double(9,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


