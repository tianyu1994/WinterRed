-- MySQL dump 10.13  Distrib 8.0.0-dmr, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: winter_red
-- ------------------------------------------------------
-- Server version	8.0.0-dmr-log

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
-- Table structure for table `current_hot_keywords`
--

DROP TABLE IF EXISTS `current_hot_keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `current_hot_keywords` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keywords` varchar(45) NOT NULL COMMENT '关键词',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `keywords_UNIQUE` (`keywords`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='当前热点关键词';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `current_hot_keywords`
--

LOCK TABLES `current_hot_keywords` WRITE;
/*!40000 ALTER TABLE `current_hot_keywords` DISABLE KEYS */;
INSERT INTO `current_hot_keywords` VALUES (6,'北京'),(3,'新冠'),(1,'新疆'),(8,'核酸检测'),(4,'病毒'),(7,'美国'),(2,'肺炎');
/*!40000 ALTER TABLE `current_hot_keywords` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-13  9:00:20
