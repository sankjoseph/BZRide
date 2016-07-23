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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `bztbl_driverinsurancedetails`
--



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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `bztbl_driverlicensedetails`
--



--
-- Table structure for table `bztbl_drivers`
--


									
CREATE TABLE IF NOT EXISTS `bztbl_drivers` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `MiddleName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(200) NOT NULL,
  `Address1` varchar(500) NOT NULL,
  `Address2` varchar(500) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `SSN` varchar(20) NOT NULL,
  `DeviceId` varchar(200) NOT NULL,
  `DeviceType` varchar(20) NOT NULL,
  `isLicenseAccepted` tinyint(1) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `status` varchar(2) NOT NULL,
  `currentlat` FLOAT,
  `currentlong` FLOAT,
  `CardType` varchar(20) NOT NULL,
  `CardProvider` varchar(20) NOT NULL,
  `cardBillingAddress1` varchar(500) NOT NULL,
  `cardBillingAddress2` varchar(500) NOT NULL,
  `CardToken` varchar(200) NOT NULL,
  `CreatedByDate` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `bztbl_drivers`
--



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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `bztbl_drivervehicledetails`
--


--
-- Table structure for table `bztbl_reportedproblems`
--

CREATE TABLE IF NOT EXISTS `bztbl_reportedproblems` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `ReportTitle` varchar(200) NOT NULL,
  `ReportDescription` varchar(500) NOT NULL,
  `CreatedByDate` datetime NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `bztbl_reportedproblems`
--


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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

--
-- Dumping data for table `bztbl_riderequests`
--


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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT AUTO_INCREMENT=1 ;

--
-- Dumping data for table `bztbl_riders`
--


--
-- Table structure for table `bztbl_userbankdetails`
--

CREATE TABLE IF NOT EXISTS `bztbl_driverbankdetails` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` varchar(10) NOT NULL,
  `AccountType` varchar(20) NOT NULL,
  `BankName` varchar(50) NOT NULL,
  `AccountToken` varchar(50) NOT NULL,
  `CreatedByDate` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `bztbl_userbankdetails`
--

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
