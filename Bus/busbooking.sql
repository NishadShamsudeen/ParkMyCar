-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2017 at 05:02 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `busbooking`
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
  `seats` varchar(72) NOT NULL,
  `fare` int(75) NOT NULL,
  `image_url` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `busdetails`
--

INSERT INTO `busdetails` (`id`, `busname`, `bustype`, `origin`, `destination`, `busdate`, `arrivaldate`, `depature`, `arrival`, `journeytime`, `seats`, `fare`, `image_url`) VALUES
(12, 'red', 'AC MULTI AXLE SEMI SLEEPER', 'kollam', 'kottayam', '2017-09-26', '2017-09-26', '05:00:00', '08:00:00', '4hrs', '40 seats', 400, 'img/redbus.png'),
(13, 'bluebus', 'AC MULTI AXLE SEMI SLEEPER', 'kochi', 'kozhikode', '2017-09-25', '2017-09-25', '07:20:00', '11:36:00', '5hrs', '40 seats', 600, 'img/bluebus.png'),
(15, 'greenbus', 'NON-AC SEMI SLEEPER', 'kollam', 'kottayam', '2017-09-26', '2017-09-26', '06:00:00', '09:00:00', '6hrs', '33 seats', 300, 'img/greenbus.png');

-- --------------------------------------------------------

--
-- Table structure for table `ticketbooked`
--

CREATE TABLE `ticketbooked` (
  `tid` int(34) NOT NULL,
  `busname` varchar(75) NOT NULL,
  `pname` varchar(75) NOT NULL,
  `page` varchar(75) NOT NULL,
  `mobile` varchar(75) NOT NULL,
  `email` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticketbooked`
--

INSERT INTO `ticketbooked` (`tid`, `busname`, `pname`, `page`, `mobile`, `email`) VALUES
(3, 'red', 'lijo4', '66', '666688', 'yyy'),
(4, 'greenbus', 'jjj', '66', 'hhh', '555'),
(5, 'greenbus', 'ghh', '6', '666', 'frr'),
(6, 'red', 'uu', '88', '55', 'jj'),
(7, 'red', '66', '88', 'ii', '88'),
(8, 'df', 'gh', '2', '23456', 'g@gmail.com'),
(9, 'sd', 'fg', '7', '23456', 'ad@gmail.com');

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
('ss', 'ss', 'ee', '6776', 'dty'),
('admin', 'admin', 'admin', '3333', 'rrrr');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `busdetails`
--
ALTER TABLE `busdetails`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ticketbooked`
--
ALTER TABLE `ticketbooked`
  ADD PRIMARY KEY (`tid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `busdetails`
--
ALTER TABLE `busdetails`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `ticketbooked`
--
ALTER TABLE `ticketbooked`
  MODIFY `tid` int(34) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
