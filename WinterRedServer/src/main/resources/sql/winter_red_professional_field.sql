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
-- Table structure for table `professional_field`
--

DROP TABLE IF EXISTS `professional_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professional_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field_name` varchar(200) NOT NULL COMMENT '专业领域名',
  `update_on` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`field_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='领域表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professional_field`
--

LOCK TABLES `professional_field` WRITE;
/*!40000 ALTER TABLE `professional_field` DISABLE KEYS */;
INSERT INTO `professional_field` VALUES (3,'哲学',NULL,NULL),(4,'经济学',NULL,NULL),(5,'法学',NULL,NULL),(6,'教育学',NULL,NULL),(7,'文学',NULL,NULL),(8,'历史学',NULL,NULL),(9,'理学',NULL,NULL),(10,'工学',NULL,NULL),(11,'农学',NULL,NULL),(12,'医学',NULL,NULL),(13,'军事学',NULL,NULL),(14,'管理学',NULL,NULL),(15,'艺术学',NULL,NULL);
/*!40000 ALTER TABLE `professional_field` ENABLE KEYS */;
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
