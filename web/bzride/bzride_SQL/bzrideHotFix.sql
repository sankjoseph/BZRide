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
INSERT INTO ` bztbl_drivers` (`Id`, `Firstname`, `MiddleName`,`Lastname`, `Email`, `Password`, `Address1`, `Address2`, `Phone`,`SSN`,`DeviceId`,`DeviceType`,`isLicenseAccepted`, 
 `isActive`,`status`, `currentlat`, `currentlong`, `CardType`, `CardProvider`, `cardBillingAddress1`, `cardBillingAddress2`, `CardToken`, CreatedByDate) VALUES
(1, 'Shenjin', 'Thomas','Sacramento', 'shenjin@outlook.com', 'passs', 'muttom', 'tpz', '0000-0000', 'sssn1', 'xxzxzxzczc', 'A', 1, 1, 'A',38.7521, 121.2880,'C', 'VISA','passs', 'muttom','zxxxx', now());


--
-- Table structure for table `bztbl_driverinsurancedetails`
--

--
-- Dumping data for table `bztbl_userbankdetails`
--

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
