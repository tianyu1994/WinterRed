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
-- Table structure for table `ask_user`
--

DROP TABLE IF EXISTS `ask_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ask_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '邮箱',
  `update_on` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ask_user`
--

LOCK TABLES `ask_user` WRITE;
/*!40000 ALTER TABLE `ask_user` DISABLE KEYS */;
INSERT INTO `ask_user` VALUES (1,'system@qq.com',NULL,NULL),(5,'wangcong@qq.com',NULL,NULL),(6,'liuming@163.com',NULL,NULL),(7,'tianyu_tianyu1994@163.com','2020-10-31 14:14:56',''),(8,'2499901339@qq.com','2020-11-02 09:37:39','');
/*!40000 ALTER TABLE `ask_user` ENABLE KEYS */;
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
