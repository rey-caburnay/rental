/****** mst_user **********/
INSERT INTO `mst_user`
(`id`,`username`,`password`, `name`,`email`,`mobile_no`,`tel_no`,`address1`,`address2`,`status`)
VALUES(1,'Rey','$2a$10$d3sElSyglbTLh5fIVMy6F.sfBFZ9L5wL5RojZ8Lj7ioBwieh1CAIO', 'Rey','Rey@email.com','09954645794','2399970','cebu city','Lapulapu City','active'),
(2,'Admin','$2a$10$d3sElSyglbTLh5fIVMy6F.sfBFZ9L5wL5RojZ8Lj7ioBwieh1CAIO', 'Rey','admin@caburnaybrokerage.com','09954645794','2399970','cebu city','Lapulapu City','active'),
(3,'Antonio','$2a$10$d3sElSyglbTLh5fIVMy6F.sfBFZ9L5wL5RojZ8Lj7ioBwieh1CAIO', 'Antonio','antonioboisertante@email.com','09954645794','2399970','cebu city','Lapulapu City','active');;

/******* mst_apartment ***********/
INSERT INTO `mst_apartment` (`id`,`name`,`personIncharge`,`aptType`,`mobileNo`,`telNo`,`address1`,`address2`,`status`)
VALUES('1','DORM 1','Antonio','Dormitory','234234234','234234','lapu-lapu City',null,'Active'),
('2','DORM 2','Antonio','Dormitory','234234234','234234','lapu-lapu City',null,'Active'),
('3','DORM 3','Antonio','Dormitory','234234234','234234','lapu-lapu City',null,'Active'),
('4','DORM 4','Antonio','Dormitory','234234234','234234','lapu-lapu City',null,'Active');



/*************** rooms *******************/
/*** DORM 1 24 ROOMS ***/
INSERT INTO `mst_room` (`aptId`, `floor`, `roomNo`,`roomDesc`, `roomType`, `size`, `status`)
VALUES ('1', '1', '1', 'room 1',  'standard', '1', 'active'),
('1', '1', '2', 'room 2', 'standard', '1', 'active'),
('1', '2', '3', 'room 3', 'standard', '1', 'active'),
('1', '1', '4', 'room 4', 'standard', '1', 'active'),
('1', '1', '5', 'room 5', 'standard', '1', 'active'),
('1', '1', '6', 'room 6', 'standard', '1', 'active'),
('1', '1', '7', 'room 7', 'standard', '1', 'active'),
('1', '1', '8', 'room 8', 'standard', '1', 'active'),
('1', '1', '9', 'room 9', 'standard', '1', 'active'),
('1', '1', '10', 'room 10', 'standard', '1', 'active'),
('1', '1', '11', 'room 11', 'standard', '1', 'active'),
('1', '1', '12', 'room 12', 'standard', '1', 'active'),
('1', '1', '13', 'room 13', 'standard', '1', 'active'),
('1', '1', '14', 'room 14', 'standard', '1', 'active'),
('1', '1', '15', 'room 15', 'standard', '1', 'active'),
('1', '1', '16', 'room 16', 'standard', '1', 'active'),
('1', '1', '17', 'room 17', 'standard', '1', 'active'),
('1', '1', '18', 'room 18', 'standard', '1', 'active'),
('1', '1', '19', 'room 19', 'standard', '1', 'active'),
('1', '1', '20', 'room 20', 'standard', '1', 'active'),
('1', '1', '21', 'room 21', 'standard', '1', 'active'),
('1', '1', '22', 'room 22', 'standard', '1', 'active'),
('1', '1', '23', 'room 23', 'standard', '1', 'active'),
('1', '1', '24', 'room 24', 'standard', '1', 'active');



/*** DORM 2 16 ROOMS ***/
INSERT INTO `mst_room` ( `aptId`, `floor`, `roomNo`,`roomDesc`, `roomType`, `size`, `status`)
VALUES ('2', '1', '1', 'room 1', 'standard', '2', 'active'),
('2', '1', '2', 'room 2', 'standard', '2', 'active'),
('2', '1', '3', 'room 3', 'standard', '2', 'active'),
('2', '1', '4', 'room 4', 'standard', '2', 'active'),
('2', '2', '5', 'room 5', 'standard', '2', 'active'),
('2', '2', '6', 'room 6', 'standard', '2', 'active'),
('2', '2', '7', 'room 7', 'standard', '2', 'active'),
('2', '2', '8', 'room 8', 'standard', '2', 'active'),
('2', '3', '9', 'room 9', 'standard', '2', 'active'),
('2', '3', '10', 'room 10', 'standard', '2', 'active'),
('2', '3', '11', 'room 11', 'standard', '2', 'active'),
('2', '3', '12', 'room 12', 'standard', '2', 'active'),
('2', '4', '13', 'room 13', 'standard', '2', 'active'),
('2', '4', '14', 'room 14', 'standard', '2', 'active'),
('2', '4', '15', 'room 15', 'standard', '2', 'active'),
('2', '4', '16', 'room 16', 'standard', '2', 'active');


/*** DORM 3 14 ROOMS ***/
INSERT INTO `mst_room` ( `aptId`, `floor`, `roomNo`,`roomDesc`, `roomType`, `size`, `status`)
VALUES ('3', '1', '1', 'room 1', 'standard', '2', 'active'),
('3', '1', '2', 'room 2', 'standard', '2', 'active'),
('3', '1', '3', 'room 3', 'standard', '2', 'active'),
('3', '1', '4', 'room 4', 'standard', '2', 'active'),
('3', '2', '5', 'room 5', 'standard', '2', 'active'),
('3', '2', '6', 'room 6', 'standard', '2', 'active'),
('3', '2', '7', 'room 7', 'standard', '2', 'active'),
('3', '2', '8', 'room 8', 'standard', '2', 'active'),
('3', '3', '9', 'room 9', 'standard', '2', 'active'),
('3', '3', '10', 'room 10', 'standard', '2', 'active'),
('3', '3', '11', 'room 11', 'standard', '2', 'active'),
('3', '3', '12', 'room 12', 'standard', '2', 'active'),
('3', '4', '13', 'room 13', 'standard', '2', 'active'),
('3', '4', '14', 'room 14', 'standard', '2', 'active');


/*** DORM 4 10 ROOMS ***/

INSERT INTO `mst_room` ( `aptId`, `floor`, `roomNo`,`roomDesc`, `roomType`, `size`, `status`)
VALUES ('4', '1', '1', 'room 1', 'standard', '4', 'active'),
('4', '1', '2', 'room 2', 'standard', '4', 'active'),
('4', '1', '3', 'room 3', 'standard', '4', 'active'),
('4', '1', '4', 'room 4', 'standard', '4', 'active'),
('4', '2', '5', 'room 5', 'standard', '4', 'active'),
('4', '2', '6', 'room 6', 'standard', '4', 'active'),
('4', '2', '7', 'room 7', 'standard', '4', 'active'),
('4', '2', '8', 'room 8', 'standard', '4', 'active'),
('4', '3', '9', 'room 9', 'standard', '4', 'active'),
('4', '3', '10', 'room 10', 'standard', '4', 'active');



--INSERT INTO `mst_room` (`id`, `aptId`, `floor`, `roomNo`, `roomType`, `size`, `status`) VALUES ('2', '1', '1', '2', 'standard', '1', 'active');
--INSERT INTO `mst_room` (`id`, `aptId`, `floor`, `roomNo`, `roomType`, `size`, `status`) VALUES ('3', '1', '2', '1', 'double', '2', 'active');
--INSERT INTO `mst_room` (`id`, `aptId`, `floor`, `roomNo`, `roomType`, `size`, `status`) VALUES ('4', '1', '2', '2', 'double', '1', 'active');
--INSERT INTO `mst_room` (`id`, `aptId`, `floor`, `roomNo`, `roomType`, `size`, `status`) VALUES ('5', '2', '1', '1', 'house', '1', 'active');

/*************** rentals ******************/

INSERT INTO `tx_rental` (`id`, `aptId`, `roomId`, `dueDate`, `txDate`, `startDate`, `endDate`, `deposit`, `renterId`, `balance`, `txType`, `amount`, `status`,`userId`) VALUES ('1', '1', '1', '2016-09-29', '2016-08-01', '2016-09-01', null, '5000', '1', '0', '1', '5000', 'active','1');
INSERT INTO `tx_rental` (`id`, `aptId`, `roomId`, `dueDate`, `txDate`, `startDate`, `endDate`, `deposit`, `renterId`, `balance`, `txType`, `amount`, `status`,`userId`) VALUES ('2', '2', '1', '2016-09-29', '2016-08-01', '2016-09-01', null, '4000', '1', '0', '1', '5000', 'active','1');
INSERT INTO `tx_rental` (`id`, `aptId`, `roomId`, `dueDate`, `txDate`, `startDate`, `endDate`, `deposit`, `renterId`, `balance`, `txType`, `amount`, `status`,`userId`) VALUES ('3', '1', '2', '2016-09-29', '2016-08-01', '2016-09-01', null, '3000', '1', '0', '1', '5000', 'active','1');

/************** renters *******************/
INSERT INTO `mst_renter` (`id`, `firstname`, `lastname`, `initial`, `email`, `mobileno`, `telno`, `address1`, `address2`, `idPresentend`, `emergencyContact`, `status`) VALUES ('1', 'Rey', 'Caburnay', 'C', 'rey@emaol.com', '09954645794', '2399292', 'Cebu', 'Cebut', 'SSS_ID', '000000', 'active');
INSERT INTO `mst_renter` (`id`, `firstname`, `lastname`, `initial`, `email`, `mobileno`, `telno`, `address1`, `address2`, `idPresentend`, `emergencyContact`, `status`) VALUES ('2', 'Bryan', 'Rey', 'C', 'br@email.com', '09954645794', '12312', 'Cebu', 'Cebu', 'Drivers License', '00000', 'active');
