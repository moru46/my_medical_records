CREATE DATABASE IF NOT EXISTS `MyMedicalRecords`
/*!40100 DEFAULT CHARACTER SET latin1 */;
USE `MyMedicalRecords`;

-- Host: 127.0.0.1  db: MyMedicalRecords

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
-- Table structure for table `prestazioni`
--


drop table if exists `prestazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
create table `prestazioni` (
    `idPrestazione` int(11) NOT NULL AUTO_INCREMENT,
    `nomeUtente` varchar(45) default null,
    `prestazione` varchar(45) default null,
    `reparto` varchar(45) default null,
    `data` date default null,
    `ora` time default null,
    `luogo` varchar(45) default null,
    primary key (`idPrestazione`)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestazioni`
--

 LOCK TABLES `prestazioni` WRITE;
/*!40000 ALTER TABLE `prestazioni` DISABLE KEYS */;
INSERT INTO `prestazioni` values 
(1,'davide@gmail.com','visita','oculistica','2020-11-06','12:30:00','livorno'),
(2,'davide@gmail.com','visita','ortopedia','2019-10-11','15:30:00','pisa'),
(3,'davide@gmail.com','visita','oculistica','2019-12-18','16:30:00','firenze'),
(4,'davide@gmail.com','visita','oculistica','2020-02-12','12:30:00','milano'),
(5,'davide@gmail.com','visita','ortopedia','2019-11-11','12:30:00','firenze'),
(6,'giovanni@alice.it','visita','ortopedia','2019-12-20','10:00:00','napoli'),
(7,'davide@gmail.com','esame','cardiologia','2019-11-11','12:00:00','livorno'),
(8,'davide@gmail.com','esame','urologia','2020-01-12','11:20:00','milano'),
(9,'davide@gmail.com','visita','oculistica','2020-11-20','12:00:00','livorno'),
(10,'rebecca@gmail.com','esame','cardiologia','2020-02-12','12:00:00','livorno'),
(11,'davide@gmail.com','visita','dermatologia','2020-10-11','13:30:00','firenze'),
(12,'rebecca@gmail.com','visita','oculistica','2020-10-16','12:30:00','pisa');
/*!40000 ALTER TABLE `prestazioni` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
	


