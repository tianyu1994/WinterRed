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
-- Table structure for table `checkman_field_relation`
--

DROP TABLE IF EXISTS `checkman_field_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkman_field_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_man_id` int(11) NOT NULL COMMENT '核查人员id',
  `professional_field_id` int(11) NOT NULL COMMENT '领域id',
  `update_on` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`check_man_id`,`professional_field_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='核查人员领域关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkman_field_relation`
--

LOCK TABLES `checkman_field_relation` WRITE;
/*!40000 ALTER TABLE `checkman_field_relation` DISABLE KEYS */;
INSERT INTO `checkman_field_relation` VALUES (2,3,12,NULL,NULL),(3,3,9,NULL,NULL),(4,3,7,NULL,NULL),(5,3,11,NULL,NULL),(6,4,9,NULL,NULL),(7,4,12,NULL,NULL),(8,4,7,NULL,NULL);
/*!40000 ALTER TABLE `checkman_field_relation` ENABLE KEYS */;
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
