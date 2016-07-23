-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 16, 2016 at 10:44 AM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bzridedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `bztbl_driverinsurancedetails`
--

CREATE TABLE IF NOT EXISTS `bztbl_driverinsurancedetails` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `DriverId` varchar(10) NOT NULL,
  `InsCompany` varchar(50) NOT NULL,
  `InsPolicyNumber` varchar(50) NOT NULL,
  `InsValidFrom` datetime NOT NULL,
  `InsExpDate` datetime NOT NULL,
  `CreatedByDate` datetime NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `bztbl_driverinsurancedetails`
--

INSERT INTO `bztbl_driverinsurancedetails` (`Id`, `DriverId`, `InsCompany`, `InsPolicyNumber`, `InsValidFrom`, `InsExpDate`, `CreatedByDate`) VALUES
(1, '', 'test4', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(2, '', 'last', 'last', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(3, '', 'National', 'hgiu87y9y', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(4, '', 'National Insurance', 'hgiu87y9y', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(5, '', '', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(6, '', '', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(7, '', '', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(8, '', '', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(9, '', '', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `bztbl_driverlicensedetails`
--

CREATE TABLE IF NOT EXISTS `bztbl_driverlicensedetails` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `driverid` int(10) NOT NULL,
  `licNumber` varchar(15) NOT NULL,
  `licStateIssued` varchar(20) NOT NULL,
  `licDateIssued` varchar(15) NOT NULL,
  `licExpDate` varchar(15) NOT NULL,
  `CreatedByDate` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `bztbl_driverlicensedetails`
--

INSERT INTO `bztbl_driverlicensedetails` (`Id`, `driverid`, `licNumber`, `licStateIssued`, `licDateIssued`, `licExpDate`, `CreatedByDate`) VALUES
(1, 0, 'last', 'last', '', '', ''),
(2, 0, 'kl456825', 'kerala', '', '', ''),
(3, 0, 'kl454525', 'Karnataka', '', '', ''),
(4, 0, '', '', '', '', ''),
(5, 0, '', '', '', '', ''),
(6, 0, '', '', '', '', ''),
(7, 0, '', '', '', '', ''),
(8, 0, '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `bztbl_drivers`
--

CREATE TABLE IF NOT EXISTS `bztbl_drivers` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(200) NOT NULL,
  `Address1` varchar(500) NOT NULL,
  `Address2` varchar(500) NOT NULL,
  `Phone` varchar(15) NOT NULL,
  `DeviceId` varchar(200) NOT NULL,
  `DeviceType` varchar(20) NOT NULL,
  `isLicenseAccepted` tinyint(1) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `status` varchar(2) NOT NULL,
  `currentlat` varchar(20) NOT NULL,
  `currentlong` varchar(20) NOT NULL,
  `CreatedByDate` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `bztbl_drivers`
--

INSERT INTO `bztbl_drivers` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Address1`, `Address2`, `Phone`, `DeviceId`, `DeviceType`, `isLicenseAccepted`, `isActive`, `status`, `currentlat`, `currentlong`, `CreatedByDate`) VALUES
(1, 'latest', '', '', '', '', '', '0', '', '', 0, 0, '', '', '', ''),
(2, 'test', 'test', 'tyty', 'trt', 'rthr', 'bgb', '0', '', '', 0, 0, '', '', '', ''),
(3, 'test 2', '', '', '', '', '', '0', '', '', 0, 0, '', '', '', ''),
(4, 'test 4', '', '', '', '', '', '0', '', '', 0, 0, '', '', '', ''),
(5, 'last test', 'last', 'last', 'last', 'lastdbz', 'lastsgd', '0', '', '', 0, 0, '', '', '', ''),
(6, 'Bibin', 'Baby', 'myemail', 'newpassword', 'myaddr1', 'myaddr2', '8086138002', 'my device id', 'my device type', 0, 0, '', '', '', ''),
(7, 'Athira', 's', 'myemail', 'newpassword', 'myaddr1', 'myaddr2', '9496144137', 'new device id', 'my device type', 0, 0, '', '', '', ''),
(8, 'Anupama', 'S', 'email1', 'pwd', 'kottayam', '', '2638', '', '', 0, 0, '', '', '', ''),
(9, 'Shyam', 'Chith', 'email2', 'renju', 'kaduthuruthy', '', '2147483647', '', '', 0, 0, '', '', '', ''),
(10, 'Aryamma', 'M', 'email3', 'neelan', 'pala', '', '9633701868', '', '', 0, 0, '', '', '', ''),
(11, 'Sooraj', 'Sudhakaran', 'sudhuemail', 'sudhu', '', '', '2147483647', '', '', 0, 0, '', '', '', ''),
(12, 'Ameer', '', 'ameeremail', '12345', '', '', '9447123456', '', '', 0, 0, '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `bztbl_drivervehicledetails`
--

CREATE TABLE IF NOT EXISTS `bztbl_drivervehicledetails` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Driverid` varchar(10) NOT NULL,
  `VehicleModel` varchar(10) NOT NULL,
  `VMake` varchar(20) NOT NULL,
  `VColor` varchar(20) NOT NULL,
  `VYear` varchar(5) NOT NULL,
  `VehicleNumber` varchar(20) NOT NULL,
  `VRegState` varchar(20) NOT NULL,
  `VDateRegistered` varchar(15) NOT NULL,
  `VExpDate` varchar(15) NOT NULL,
  `CreatedByDate` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `bztbl_drivervehicledetails`
--

INSERT INTO `bztbl_drivervehicledetails` (`Id`, `Driverid`, `VehicleModel`, `VMake`, `VColor`, `VYear`, `VehicleNumber`, `VRegState`, `VDateRegistered`, `VExpDate`, `CreatedByDate`) VALUES
(1, '', 'breeza', 'Maruthy', 'supreme white', '2016', 'KL-38D-3838', 'Kerala', '', '', ''),
(2, '', 'dzire', 'Maruthy', 'supreme white', '2016', 'KL-38D-1', 'Kerala', '', '', ''),
(3, '', 'camry', 'toyota', 'white', '', 'kl 5 aa 6608', 'kerala', '', '', ''),
(4, '', '', '', '', '', '', '', '', '', ''),
(5, '', '', '', '', '', '', '', '', '', ''),
(6, '', '', '', '', '', '', '', '', '', ''),
(7, '', '', '', '', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `bztbl_reportedproblems`
--

CREATE TABLE IF NOT EXISTS `bztbl_reportedproblems` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `ReportTitle` varchar(200) NOT NULL,
  `ReportDescription` varchar(500) NOT NULL,
  `CreatedByDate` datetime NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `bztbl_reportedproblems`
--

INSERT INTO `bztbl_reportedproblems` (`Id`, `ReportTitle`, `ReportDescription`, `CreatedByDate`) VALUES
(1, '', '', '0000-00-00 00:00:00'),
(2, 'vehicle not clean', 'not good', '0000-00-00 00:00:00'),
(3, 'vehicle not clean', 'not good', '0000-00-00 00:00:00'),
(4, 'vehicle not clean', 'not good', '0000-00-00 00:00:00'),
(5, 'vehicle not clean', 'not good', '0000-00-00 00:00:00'),
(6, '', '', '0000-00-00 00:00:00'),
(7, '', '', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `bztbl_riderequests`
--

CREATE TABLE IF NOT EXISTS `bztbl_riderequests` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `RequestType` varchar(2) NOT NULL,
  `RequestorId` varchar(10) NOT NULL,
  `DriverId` varchar(10) NOT NULL,
  `StartLocation` varchar(200) NOT NULL,
  `EndLocation` varchar(200) NOT NULL,
  `StartLat` varchar(50) NOT NULL,
  `StartLong` varchar(50) NOT NULL,
  `EndLat` varchar(50) NOT NULL,
  `EndLong` varchar(50) NOT NULL,
  `Status` varchar(2) NOT NULL,
  `RideDate` datetime NOT NULL,
  `RideTime` datetime NOT NULL,
  `ActualRideDate` datetime NOT NULL,
  `ActualRideTime` datetime NOT NULL,
  `CreatedByDate` datetime NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `bztbl_riderequests`
--

INSERT INTO `bztbl_riderequests` (`Id`, `RequestType`, `RequestorId`, `DriverId`, `StartLocation`, `EndLocation`, `StartLat`, `StartLong`, `EndLat`, `EndLong`, `Status`, `RideDate`, `RideTime`, `ActualRideDate`, `ActualRideTime`, `CreatedByDate`) VALUES
(1, '1', '', '', '', '', '', '', '', '', 'a', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(2, '1', '', '', '', '', '', '', '', '', 'a', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `bztbl_riders`
--

CREATE TABLE IF NOT EXISTS `bztbl_riders` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `MiddleName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(200) NOT NULL,
  `Address1` varchar(500) NOT NULL,
  `Address2` varchar(500) NOT NULL,
  `Phone` varchar(15) NOT NULL,
  `DeviceId` varchar(200) NOT NULL,
  `DeviceType` varchar(20) NOT NULL,
  `isLicenseAccepted` tinyint(1) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `CardType` varchar(20) NOT NULL,
  `CardProvider` varchar(20) NOT NULL,
  `cardBillingAddress1` varchar(500) NOT NULL,
  `cardBillingAddress2` varchar(500) NOT NULL,
  `CardToken` varchar(200) NOT NULL,
  `CreatedByDate` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT AUTO_INCREMENT=7 ;

--
-- Dumping data for table `bztbl_riders`
--

INSERT INTO `bztbl_riders` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Address1`, `Address2`, `Phone`, `DeviceId`, `DeviceType`, `isLicenseAccepted`, `isActive`, `CardType`, `CardProvider`, `CardToken`, `cardBillingAddress1`, `cardBillingAddress2`, `CreatedByDate`) VALUES
(1, 'test', 'test', '', '', 'test', 'test', '0', '', '', 0, 0, '', '', '','','', ''),
(2, 'Ameer', 'Amee', 'myemail', 'newpassword', 'my addr1', 'myaddr2', '234466', '44455588', 'android', 0, 0, 'debit', 'mastro', '','','', ''),
(3, 'Ameer', 'Amee', 'myemail', 'newpassword', 'my addr1', 'myaddr2', '234466', '44455588', 'android', 0, 0, 'debit', 'mastro', '','','', ''),
(4, 'Ameer', 'Amee', 'myemail', 'newpassword', 'my addr1', 'myaddr2', '234466', '44455588', 'android', 0, 0, 'debit', 'mastro', '', '','',''),
(5, 'Aryamma', 'm', 'aryaemail', '12345', '', '', '9633701868', '', '', 0, 0, '', '', '','','', ''),
(6, 'Athira', 'Satheesh', 'athiraemail', '12345', '', '', '9496425317', '', '', 0, 0, '', '', '','','', '');

-- --------------------------------------------------------

--
-- Table structure for table `bztbl_userbankdetails`
--

CREATE TABLE IF NOT EXISTS `bztbl_driverbankdetails` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` varchar(10) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `BankName` varchar(50) NOT NULL,
  `AccountNumber` varchar(50) NOT NULL,
  `AccountHoldername` varchar(60) NOT NULL,
  `RoutingNumber` varchar(50) NOT NULL,
  `CreatedByDate` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `bztbl_userbankdetails`
--

INSERT INTO `bztbl_driverbankdetails` (`Id`, `userid`, `Type`, `BankName`, `AccountNumber`, `AccountHoldername`, `RoutingNumber`, `CreatedByDate`) VALUES
(1, '', '', 'SIB', '15542652653', 'AMEER', '123', ''),
(2, '', '', 'SIB', '15542652653', 'AMEER', '123', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
