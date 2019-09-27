-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.4.6-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for assign
DROP DATABASE IF EXISTS `assign`;
CREATE DATABASE IF NOT EXISTS `assign` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `assign`;

-- Dumping structure for table assign.author
DROP TABLE IF EXISTS `author`;
CREATE TABLE IF NOT EXISTS `author` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table assign.enum_item
DROP TABLE IF EXISTS `enum_item`;
CREATE TABLE IF NOT EXISTS `enum_item` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `enum_item_code` varchar(50) NOT NULL,
  `enam_item_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `enum_item_code` (`enum_item_code`,`enam_item_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table assign.publication
DROP TABLE IF EXISTS `publication`;
CREATE TABLE IF NOT EXISTS `publication` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `year` date NOT NULL,
  `hero` varchar(200) DEFAULT NULL,
  `publication_enum_id` int(10) NOT NULL,
  `magazine_type_enum_id` int(10) DEFAULT NULL,
  `book_genre_enum_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_publication_enum_item` (`publication_enum_id`),
  KEY `FK_publication_enum_item_2` (`magazine_type_enum_id`),
  KEY `FK_publication_enum_item_3` (`book_genre_enum_id`),
  CONSTRAINT `FK_publication_enum_item` FOREIGN KEY (`publication_enum_id`) REFERENCES `enum_item` (`id`),
  CONSTRAINT `FK_publication_enum_item_2` FOREIGN KEY (`magazine_type_enum_id`) REFERENCES `enum_item` (`id`),
  CONSTRAINT `FK_publication_enum_item_3` FOREIGN KEY (`book_genre_enum_id`) REFERENCES `enum_item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table assign.publication_author
DROP TABLE IF EXISTS `publication_author`;
CREATE TABLE IF NOT EXISTS `publication_author` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `publication_id` bigint(20) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `publication_author_id` (`publication_id`,`author_id`),
  UNIQUE KEY `UKqd533pgw12cho9pn0lkfc5i7r` (`author_id`,`publication_id`)
) ENGINE=MyISAM AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.4.6-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table assign.enum_item: ~8 rows (approximately)
/*!40000 ALTER TABLE `enum_item` DISABLE KEYS */;
INSERT INTO `enum_item` (`id`, `enum_item_code`, `enam_item_name`) VALUES
	(8, 'BOOK', 'Detective'),
	(6, 'BOOK', 'Drama'),
	(7, 'BOOK', 'Novel'),
	(5, 'MAGAZINE', 'On-line'),
	(4, 'MAGAZINE', 'Printed'),
	(3, 'PUBLICATION', 'Book'),
	(2, 'PUBLICATION', 'Comics'),
	(1, 'PUBLICATION', 'Magazine');
/*!40000 ALTER TABLE `enum_item` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

