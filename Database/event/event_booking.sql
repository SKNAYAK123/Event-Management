-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: event
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `del_status` int NOT NULL,
  `equipment_cost` int NOT NULL,
  `equipment_name` varchar(255) DEFAULT NULL,
  `event_cost` int NOT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `food_item_cost` int NOT NULL,
  `food_item_name` varchar(255) DEFAULT NULL,
  `guest_count` int NOT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `selected_equipments` varchar(255) DEFAULT NULL,
  `selected_food_items` varchar(255) DEFAULT NULL,
  `total_cost` int NOT NULL,
  `member_member_id` int DEFAULT NULL,
  `venue_venue_id` int DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `FKthli5k3st0i1eboo63jgyvbkp` (`member_member_id`),
  KEY `FK7d4bwqeodtwesw51q16q6qjnp` (`venue_venue_id`),
  CONSTRAINT `FK7d4bwqeodtwesw51q16q6qjnp` FOREIGN KEY (`venue_venue_id`) REFERENCES `venue` (`venue_id`),
  CONSTRAINT `FKthli5k3st0i1eboo63jgyvbkp` FOREIGN KEY (`member_member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'2023-08-10',111,7000,'  camera dj',10000,'wedding ',7000,'  ice cream 3 ice cream',100,'Processed',NULL,NULL,24000,3,1),(2,'2023-08-31',111,14000,'  camera',100000,'wedding ',2000,'  ice cream',100,'Processed',NULL,NULL,116000,3,4);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-26 16:08:45
