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
-- Table structure for table `check_plat`
--

DROP TABLE IF EXISTS `check_plat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_plat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_name` varchar(200) NOT NULL COMMENT '机构名称',
  `belong_area` varchar(200) NOT NULL COMMENT '所属地区',
  `update_on` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`organization_name`,`belong_area`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COMMENT='核查平台表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_plat`
--

LOCK TABLES `check_plat` WRITE;
/*!40000 ALTER TABLE `check_plat` DISABLE KEYS */;
INSERT INTO `check_plat` VALUES (1,'环球网','中国','2020-11-12 19:53:00','系统扫描'),(2,'红星新闻','中国','2020-11-12 19:59:55','系统扫描'),(3,'新京报','中国','2020-11-12 20:00:01','系统扫描'),(4,'北京青年报','中国','2020-11-12 20:00:01','系统扫描'),(5,'北京晚报','中国','2020-11-12 20:00:01','系统扫描'),(6,'药葫芦娃','中国','2020-11-12 20:00:01','系统扫描'),(7,'上观新闻','中国','2020-11-12 20:00:01','系统扫描'),(8,'环球时报','中国','2020-11-12 20:00:01','系统扫描'),(9,'星洲日报','中国','2020-11-12 20:00:01','系统扫描'),(10,'英国报姐英语世界','中国','2020-11-12 20:00:01','系统扫描'),(11,'科普中国','中国','2020-11-12 20:00:01','系统扫描'),(12,'新华网','中国','2020-11-12 20:00:01','系统扫描'),(13,'黑龙江省牡丹江市委网信办','中国','2020-11-12 20:00:01','系统扫描'),(14,'健康时报','中国','2020-11-12 20:00:01','系统扫描'),(15,'北京时间','中国','2020-11-12 20:00:01','系统扫描'),(16,'华商报','中国','2020-11-12 20:00:01','系统扫描'),(17,'柳州晚报','中国','2020-11-12 20:00:01','系统扫描'),(18,'人民日报','中国','2020-11-12 20:00:01','系统扫描'),(19,'医师报','中国','2020-11-12 20:00:01','系统扫描'),(20,'百度辟谣','中国','2020-11-12 20:00:01','系统扫描'),(21,'澎湃新闻','中国','2020-11-12 20:00:01','系统扫描'),(22,'上海网络辟谣平台','中国','2020-11-12 20:00:01','系统扫描'),(23,'中华人民共和国住法兰西共和国大使馆','中国','2020-11-12 20:00:01','系统扫描'),(24,'中华人民共和国教育部','中国','2020-11-12 20:00:01','系统扫描'),(25,'中国新闻网','中国','2020-11-12 20:00:01','系统扫描'),(26,'人民网','中国','2020-11-12 20:00:01','系统扫描'),(27,'央视新闻','中国','2020-11-12 20:00:01','系统扫描'),(28,'中国青年网','中国','2020-11-12 20:00:01','系统扫描'),(29,'楚天都市报','中国','2020-11-12 20:00:01','系统扫描'),(30,'21世纪经济报道','中国','2020-11-12 20:00:01','系统扫描'),(31,'封面新闻','中国','2020-11-12 20:00:01','系统扫描'),(32,'《国家地理》','中国','2020-11-12 20:00:01','系统扫描'),(33,'中国足球协会官网','中国','2020-11-12 20:00:01','系统扫描'),(34,'航运界','中国','2020-11-12 20:00:01','系统扫描'),(35,'潇湘晨报','中国','2020-11-12 20:00:01','系统扫描'),(36,'百度体育','中国','2020-11-12 20:00:01','系统扫描'),(37,'科学辟谣','中国','2020-11-12 20:00:01','系统扫描'),(38,'东方网','中国','2020-11-12 20:00:01','系统扫描'),(39,'NBA官方微博 人民日报海外网 ','中国','2020-11-12 20:00:01','系统扫描'),(40,'中国青年报','中国','2020-11-12 20:00:01','系统扫描'),(41,'中华人民共和国驻英国大使馆教育处官网','中国','2020-11-12 20:00:01','系统扫描'),(42,'中国教育新闻网 ','中国','2020-11-12 20:00:01','系统扫描'),(43,'旅法华人战报','中国','2020-11-12 20:00:01','系统扫描'),(44,'每日经济新闻','中国','2020-11-12 20:00:01','系统扫描'),(45,'王亚双 大连市金州区第二人民医院医师','中国','2020-11-12 20:00:01','系统扫描'),(46,'新民晚报','中国','2020-11-12 20:00:01','系统扫描'),(47,'财新网','中国','2020-11-12 20:00:01','系统扫描'),(48,'半岛晨报 ','中国','2020-11-12 20:00:01','系统扫描');
/*!40000 ALTER TABLE `check_plat` ENABLE KEYS */;
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
