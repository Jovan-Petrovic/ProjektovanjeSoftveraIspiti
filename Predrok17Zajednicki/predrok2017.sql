-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 04, 2020 at 03:28 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `predrok2017`
--

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `korisnikID` int(11) NOT NULL,
  `ime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `korisnickoIme` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`korisnikID`, `ime`, `prezime`, `korisnickoIme`) VALUES
(1, 'Jovan', 'Petrovic', 'Petra'),
(2, 'Stevan ', 'Radovanocic', 'Stevica'),
(5, 'MAtija', 'Radicevic', 'Gen Z');

-- --------------------------------------------------------

--
-- Table structure for table `prevod`
--

CREATE TABLE `prevod` (
  `prevodID` int(11) NOT NULL,
  `recID` int(11) NOT NULL,
  `prevod` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `korisnikID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `prevod`
--

INSERT INTO `prevod` (`prevodID`, `recID`, `prevod`, `korisnikID`) VALUES
(1, 1, 'Horse', 1),
(2, 1, 'equine', 2),
(5, 2, 'book', 2),
(6, 2, 'volume', 5);

-- --------------------------------------------------------

--
-- Table structure for table `rec`
--

CREATE TABLE `rec` (
  `recID` int(11) NOT NULL,
  `rec` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `korisnikID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `rec`
--

INSERT INTO `rec` (`recID`, `rec`, `korisnikID`) VALUES
(1, 'Konj', 1),
(2, 'Knjiga', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`korisnikID`);

--
-- Indexes for table `prevod`
--
ALTER TABLE `prevod`
  ADD PRIMARY KEY (`prevodID`),
  ADD KEY `recID` (`recID`),
  ADD KEY `korisnikID` (`korisnikID`);

--
-- Indexes for table `rec`
--
ALTER TABLE `rec`
  ADD PRIMARY KEY (`recID`),
  ADD KEY `korisnikID` (`korisnikID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `korisnikID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `prevod`
--
ALTER TABLE `prevod`
  MODIFY `prevodID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `rec`
--
ALTER TABLE `rec`
  MODIFY `recID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `prevod`
--
ALTER TABLE `prevod`
  ADD CONSTRAINT `prevod_ibfk_1` FOREIGN KEY (`recID`) REFERENCES `rec` (`recID`),
  ADD CONSTRAINT `prevod_ibfk_2` FOREIGN KEY (`korisnikID`) REFERENCES `korisnik` (`korisnikID`);

--
-- Constraints for table `rec`
--
ALTER TABLE `rec`
  ADD CONSTRAINT `rec_ibfk_1` FOREIGN KEY (`korisnikID`) REFERENCES `korisnik` (`korisnikID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
