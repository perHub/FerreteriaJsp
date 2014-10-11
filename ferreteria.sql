CREATE DATABASE  IF NOT EXISTS `ferreteria` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ferreteria`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: ferreteria
-- ------------------------------------------------------
-- Server version	5.6.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compras` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `total` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (1,'2014-09-13',351),(4,'2014-09-15',100.2),(5,'2014-09-16',50.2),(6,'2014-09-16',100.6),(7,'2014-09-19',110.2),(8,'2014-09-21',110.2),(9,'2014-09-21',50),(10,'2014-09-21',64),(11,'2014-09-21',1248),(12,'2014-09-21',20),(13,'2014-09-22',34),(14,'2014-09-23',1330);
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras_detalles`
--

DROP TABLE IF EXISTS `compras_detalles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compras_detalles` (
  `compras_id` int(11) NOT NULL,
  `detalles_id` int(11) NOT NULL,
  PRIMARY KEY (`compras_id`,`detalles_id`),
  UNIQUE KEY `UK_7wfg6ijigjmn6rlh24sahhk78` (`detalles_id`),
  UNIQUE KEY `UK_kfc69qcyg4td82056rmg3t2d0` (`detalles_id`),
  KEY `FK_7wfg6ijigjmn6rlh24sahhk78` (`detalles_id`),
  KEY `FK_7vcycp39cdavvnlo6naq0gct` (`compras_id`),
  CONSTRAINT `FK_7vcycp39cdavvnlo6naq0gct` FOREIGN KEY (`compras_id`) REFERENCES `compras` (`id`),
  CONSTRAINT `FK_7wfg6ijigjmn6rlh24sahhk78` FOREIGN KEY (`detalles_id`) REFERENCES `detalles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras_detalles`
--

LOCK TABLES `compras_detalles` WRITE;
/*!40000 ALTER TABLE `compras_detalles` DISABLE KEYS */;
INSERT INTO `compras_detalles` VALUES (1,1),(1,2),(1,3),(4,5),(4,6),(4,7),(5,8),(5,9),(6,10),(6,11),(6,12),(7,13),(7,14),(7,15),(8,16),(8,17),(8,18),(9,19),(9,20),(10,21),(10,22),(11,23),(11,24),(11,25),(12,26),(12,27),(13,28),(13,29),(14,30),(14,31),(14,32);
/*!40000 ALTER TABLE `compras_detalles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles`
--

DROP TABLE IF EXISTS `detalles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `producto_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ros52b0tnfu4stvho1vlj38pw` (`producto_id`),
  CONSTRAINT `FK_ros52b0tnfu4stvho1vlj38pw` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles`
--

LOCK TABLES `detalles` WRITE;
/*!40000 ALTER TABLE `detalles` DISABLE KEYS */;
INSERT INTO `detalles` VALUES (1,2,100,2),(2,5,250,2),(3,5,1,1),(5,1,50,2),(6,1,0.2,1),(7,1,50,2),(8,1,50,2),(9,1,0.2,1),(10,1,50,2),(11,1,50,2),(12,3,0.6,1),(13,1,60,5),(14,1,50,2),(15,1,0.2,1),(16,1,0.2,1),(17,2,100,2),(18,50,10,4),(19,1,30,8),(20,2,20,6),(21,70,14,1),(22,1,50,2),(23,1,1200,3),(24,90,18,4),(25,1,30,8),(26,1,10,6),(27,1,10,7),(28,100,20,1),(29,70,14,4),(30,1,10,7),(31,2,120,5),(32,1,1200,3);
/*!40000 ALTER TABLE `detalles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Tornillos',0.2,4818),(2,'Martillo',50,486),(3,'Taladro',1200,298),(4,'Clavos',0.2,4790),(5,'Pinza',60,47),(6,'Destornillador philips',10,97),(7,'Destornillador plano',10,98),(8,'Tenaza',30,48);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `tipousr` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activo` tinyint(1) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('cliente',1,1,'CL1','Cliente 1','cliente1','cl1',9999999),('cliente',2,1,'CL2','Cliente 2','cliente2','cl2',12781),('cliente',3,0,'CL3','Cliente 3','cliente3','cl3',12),('admin',4,1,'admin','r00t','admin','admin',NULL),('cliente',5,1,'Cl4','Cl4','cliente4','cl4',175);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_compras`
--

DROP TABLE IF EXISTS `usuarios_compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios_compras` (
  `usuarios_id` int(11) NOT NULL,
  `compras_id` int(11) NOT NULL,
  PRIMARY KEY (`usuarios_id`,`compras_id`),
  UNIQUE KEY `UK_ftl5fiy7jbl30wl1blvvgmhks` (`compras_id`),
  KEY `FK_ftl5fiy7jbl30wl1blvvgmhks` (`compras_id`),
  KEY `FK_jeeb7n62urc83knr9ly79d9ss` (`usuarios_id`),
  CONSTRAINT `FK_jeeb7n62urc83knr9ly79d9ss` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FK_ftl5fiy7jbl30wl1blvvgmhks` FOREIGN KEY (`compras_id`) REFERENCES `compras` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_compras`
--

LOCK TABLES `usuarios_compras` WRITE;
/*!40000 ALTER TABLE `usuarios_compras` DISABLE KEYS */;
INSERT INTO `usuarios_compras` VALUES (2,8),(2,9),(1,10),(1,11),(1,12),(5,13),(5,14);
/*!40000 ALTER TABLE `usuarios_compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ferreteria'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-23 21:11:29
