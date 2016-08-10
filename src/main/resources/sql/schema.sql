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
  `roomType` varchar(20) DEFAULT NULL,
  `size` int(5) DEFAULT 0,
  `occupied` int(5) DEFAULT 0,
  `telNo` varchar(45) DEFAULT NULL,
  `rate` double(9,4) DEFAULT 0,
  `status`varchar(20) DEFAULT NULL,
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
  `amount` double(9,4) DEFAULT 0.000,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/**** electric ***/
CREATE TABLE `mst_electric` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aptId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `descripton` varchar(50) DEFAULT NULL,
  `curReading` int(11) DEFAULT 0,
  `prevReading` int(11) DEFAULT 0,
  `provider` varchar(50) DEFAULT NULL,
  `amount` double(9,4) DEFAULT 0.000,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`,`roomId`)
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


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
  `status` varchar(20) DEFAULT NULL,
  `userId` int(11) not null,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`,`aptId`,`roomId`)
  )ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;