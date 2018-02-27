-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 26, 2017 at 11:39 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bus101`
--

-- --------------------------------------------------------

--
-- Table structure for table `busdetails`
--

CREATE TABLE `busdetails` (
  `id` int(32) NOT NULL,
  `busname` varchar(32) NOT NULL,
  `bustype` varchar(75) NOT NULL,
  `origin` text NOT NULL,
  `destination` text NOT NULL,
  `busdate` varchar(30) NOT NULL,
  `arrivaldate` date NOT NULL,
  `depature` time NOT NULL,
  `arrival` time NOT NULL,
  `journeytime` varchar(75) NOT NULL,
  `seats` int(32) NOT NULL,
  `fare` int(75) NOT NULL,
  `image_url` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `busdetails`
--

INSERT INTO `busdetails` (`id`, `busname`, `bustype`, `origin`, `destination`, `busdate`, `arrivaldate`, `depature`, `arrival`, `journeytime`, `seats`, `fare`, `image_url`) VALUES
(12, 'red', 'AC MULTI AXLE SEMI SLEEPER', 'kollam', 'kottayam', '2017-09-26', '2017-09-26', '05:00:00', '08:00:00', '4hrs', 40, 800, 'img/redbus.png'),
(13, 'bluebus', 'AC MULTI AXLE SEMI SLEEPER', 'kochi', 'kozhikode', '2017-09-25', '2017-09-25', '07:20:00', '11:36:00', '5hrs', 40, 600, 'img/bluebus.png'),
(15, 'greenbus', 'NON-AC SEMI SLEEPER', 'kollam', 'kottayam', '2017-09-26', '2017-09-26', '06:00:00', '09:00:00', '6hrs', 33, 400, 'img/greenbus.png'),
(16, 'Yellow Bus', 'AC SEMI SLEEPER', 'kollam', 'kottayam', '2017-09-26', '2017-09-26', '02:00:00', '05:00:00', '3hrs', 45, 650, 'img/yellowbus.png');

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE `userinfo` (
  `name` varchar(75) NOT NULL,
  `email` varchar(75) NOT NULL,
  `password` varchar(75) NOT NULL,
  `phone` varchar(75) NOT NULL,
  `address` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`name`, `email`, `password`, `phone`, `address`) VALUES
('aaa', 'hhh', '1', '444', 'ggg'),
('ss', 'ss', 'ee', '6776', 'dty');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `busdetails`
--
ALTER TABLE `busdetails`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `busdetails`
--
ALTER TABLE `busdetails`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
