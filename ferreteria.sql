-- phpMyAdmin SQL Dump
-- version 4.2.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 15, 2014 at 02:59 AM
-- Server version: 5.5.39
-- PHP Version: 5.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ferreteria`
--
DROP DATABASE `ferreteria`;
CREATE DATABASE IF NOT EXISTS `ferreteria` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `ferreteria`;

-- --------------------------------------------------------

--
-- Table structure for table `compras`
--

CREATE TABLE `compras` (
`id` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `total` float DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `compras`
--

INSERT INTO `compras` (`id`, `fecha`, `total`) VALUES
(1, '2014-09-13', 351),
(4, '2014-09-15', 100.2),
(5, '2014-09-16', 50.2),
(6, '2014-09-16', 100.6),
(7, '2014-09-19', 110.2),
(8, '2014-09-21', 110.2),
(9, '2014-09-21', 50),
(10, '2014-09-21', 64),
(11, '2014-09-21', 1248),
(12, '2014-09-21', 20),
(13, '2014-09-22', 34),
(14, '2014-09-23', 1330),
(16, '2014-10-11', 0.4);

-- --------------------------------------------------------

--
-- Table structure for table `compras_detalles`
--

CREATE TABLE `compras_detalles` (
  `compras_id` int(11) NOT NULL,
  `detalles_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `compras_detalles`
--

INSERT INTO `compras_detalles` (`compras_id`, `detalles_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(4, 5),
(4, 6),
(4, 7),
(5, 8),
(5, 9),
(6, 10),
(6, 11),
(6, 12),
(7, 13),
(7, 14),
(7, 15),
(8, 16),
(8, 17),
(8, 18),
(9, 19),
(9, 20),
(10, 21),
(10, 22),
(11, 23),
(11, 24),
(11, 25),
(12, 26),
(12, 27),
(13, 28),
(13, 29),
(14, 30),
(14, 31),
(14, 32),
(16, 34);

-- --------------------------------------------------------

--
-- Table structure for table `detalles`
--

CREATE TABLE `detalles` (
`id` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `producto_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `detalles`
--

INSERT INTO `detalles` (`id`, `cantidad`, `precio`, `producto_id`) VALUES
(1, 2, 100, 2),
(2, 5, 250, 2),
(3, 5, 1, 1),
(5, 1, 50, 2),
(6, 1, 0.2, 1),
(7, 1, 50, 2),
(8, 1, 50, 2),
(9, 1, 0.2, 1),
(10, 1, 50, 2),
(11, 1, 50, 2),
(12, 3, 0.6, 1),
(13, 1, 60, 5),
(14, 1, 50, 2),
(15, 1, 0.2, 1),
(16, 1, 0.2, 1),
(17, 2, 100, 2),
(18, 50, 10, 4),
(19, 1, 30, 8),
(20, 2, 20, 6),
(21, 70, 14, 1),
(22, 1, 50, 2),
(23, 1, 1200, 3),
(24, 90, 18, 4),
(25, 1, 30, 8),
(26, 1, 10, 6),
(27, 1, 10, 7),
(28, 100, 20, 1),
(29, 70, 14, 4),
(30, 1, 10, 7),
(31, 2, 120, 5),
(32, 1, 1200, 3),
(34, 2, 0.4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `productos`
--

CREATE TABLE `productos` (
`id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `precio`, `stock`, `activo`) VALUES
(1, 'Tornillos', 0.2, 4816, 1),
(2, 'Martillo', 50, 486, 1),
(3, 'Taladro', 1200, 298, 1),
(4, 'Clavos', 0.2, 4790, 1),
(5, 'Pinza', 60, 47, 1),
(6, 'Destornillador philips', 10, 97, 1),
(7, 'Destornillador plano', 10, 98, 1),
(8, 'Tenaza', 30, 48, 1),
(9, 'Sargento', 200, 100, 0);

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `tipousr` varchar(31) NOT NULL,
`id` int(11) NOT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`tipousr`, `id`, `activo`, `apellido`, `nombre`, `password`, `username`, `dni`) VALUES
('cliente', 1, 1, 'CL1', 'Cliente 1', 'cliente1', 'cl1', 9999999),
('cliente', 2, 1, 'CL2', 'Cliente 2', 'cliente2', 'cl2', 12781),
('cliente', 3, 0, 'CL3', 'Cliente 3', 'cliente3', 'cl3', 12),
('admin', 4, 1, 'admin', 'r00t', 'admin', 'admin', NULL),
('cliente', 5, 1, 'Cl4', 'Cl4', 'cliente4', 'cl4', 175);

-- --------------------------------------------------------

--
-- Table structure for table `usuarios_compras`
--

CREATE TABLE `usuarios_compras` (
  `usuarios_id` int(11) NOT NULL,
  `compras_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuarios_compras`
--

INSERT INTO `usuarios_compras` (`usuarios_id`, `compras_id`) VALUES
(2, 8),
(2, 9),
(1, 10),
(1, 11),
(1, 12),
(5, 13),
(5, 14),
(2, 16);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `compras`
--
ALTER TABLE `compras`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `compras_detalles`
--
ALTER TABLE `compras_detalles`
 ADD PRIMARY KEY (`compras_id`,`detalles_id`), ADD UNIQUE KEY `UK_7wfg6ijigjmn6rlh24sahhk78` (`detalles_id`), ADD UNIQUE KEY `UK_kfc69qcyg4td82056rmg3t2d0` (`detalles_id`), ADD KEY `FK_7wfg6ijigjmn6rlh24sahhk78` (`detalles_id`), ADD KEY `FK_7vcycp39cdavvnlo6naq0gct` (`compras_id`);

--
-- Indexes for table `detalles`
--
ALTER TABLE `detalles`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_ros52b0tnfu4stvho1vlj38pw` (`producto_id`);

--
-- Indexes for table `productos`
--
ALTER TABLE `productos`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuarios_compras`
--
ALTER TABLE `usuarios_compras`
 ADD PRIMARY KEY (`usuarios_id`,`compras_id`), ADD UNIQUE KEY `UK_ftl5fiy7jbl30wl1blvvgmhks` (`compras_id`), ADD KEY `FK_ftl5fiy7jbl30wl1blvvgmhks` (`compras_id`), ADD KEY `FK_jeeb7n62urc83knr9ly79d9ss` (`usuarios_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `compras`
--
ALTER TABLE `compras`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `detalles`
--
ALTER TABLE `detalles`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT for table `productos`
--
ALTER TABLE `productos`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `compras_detalles`
--
ALTER TABLE `compras_detalles`
ADD CONSTRAINT `FK_7vcycp39cdavvnlo6naq0gct` FOREIGN KEY (`compras_id`) REFERENCES `compras` (`id`),
ADD CONSTRAINT `FK_7wfg6ijigjmn6rlh24sahhk78` FOREIGN KEY (`detalles_id`) REFERENCES `detalles` (`id`);

--
-- Constraints for table `detalles`
--
ALTER TABLE `detalles`
ADD CONSTRAINT `FK_ros52b0tnfu4stvho1vlj38pw` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`);

--
-- Constraints for table `usuarios_compras`
--
ALTER TABLE `usuarios_compras`
ADD CONSTRAINT `FK_ftl5fiy7jbl30wl1blvvgmhks` FOREIGN KEY (`compras_id`) REFERENCES `compras` (`id`),
ADD CONSTRAINT `FK_jeeb7n62urc83knr9ly79d9ss` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
