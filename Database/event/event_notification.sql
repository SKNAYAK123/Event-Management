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
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notification_id` int NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `member_id` int NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'2023-08-10',1,'Welcome SAHIL, You have successfully registered on Online Event Management System. Now you can add your venues on the portal','03:51:54'),(2,'2023-08-10',1,'Dear SAHIL Your venue with name Aster Banquet Hall at place Bangalore is Successfully added. ','03:58:58'),(3,'2023-08-10',1,'Dear SAHIL Your venue with name Hummingbird Hall at place Hyderabad is Successfully added. ','04:05:51'),(4,'2023-08-10',1,'Dear SAHIL Your venue with name The Green Banquets at place Kolkata is Successfully added. ','04:06:56'),(5,'2023-08-10',1,'Dear SAHIL Your venue with name The Azalea Hall at place Chennai is Successfully added. ','04:07:31'),(6,'2023-08-10',1,'Dear SAHIL Your venue with name Nightjar Banquets at place Mumbai is Successfully added. ','04:08:08'),(7,'2023-08-10',1,'Dear SAHIL Your venue with name Citrus Halls at place Jaipur is Successfully added. ','04:08:32'),(8,'2023-08-10',1,'Dear SAHIL Your venue with name Buttercup Ballrooms at place Pune is Successfully added. ','04:09:12'),(9,'2023-08-10',1,'Dear SAHIL Your venue with name Falcon Halls at place  Ahmedabad is Successfully added. ','04:09:44'),(10,'2023-08-10',3,'Welcome SAHIL, You have successfully registered on Online Event Management System. Now you can book your events on various venues.','04:18:29'),(11,'2023-08-10',3,'Dear User, Your booking data is stored temprory, Please make it fixed by completing payment','04:19:41'),(12,'2023-08-10',3,'Dear SAHIL, Your payment of Rs. 24000 for booking ID 1 is processed successfully.Your wedding  event is booked on Venue Name Aster Banquet Hall at Bangalore on date 2023-08-10','04:21:26'),(13,'2023-08-10',1,'Dear SAHIL, Your venue Aster Banquet Hall is booked by user SAHIL NAYAK for an event of wedding  on date 2023-08-10','04:21:26'),(14,'2023-08-18',3,'Dear User, Your booking data is stored temprory, Please make it fixed by completing payment','19:17:50'),(15,'2023-08-18',3,'Dear SAHIL, Your payment of Rs. 116000 for booking ID 2 is processed successfully.Your wedding  event is booked on Venue Name The Azalea Hall at Chennai on date 2023-08-31','19:18:49'),(16,'2023-08-18',1,'Dear SAHIL, Your venue The Azalea Hall is booked by user SAHIL NAYAK for an event of wedding  on date 2023-08-31','19:18:49'),(17,'2023-08-18',1,'Dear SAHIL Your venue with name chinu at place mumbai is Successfully added. ','19:20:55');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-26 16:08:46
