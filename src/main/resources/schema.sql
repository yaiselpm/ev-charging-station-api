CREATE TABLE IF NOT EXISTS `charging_station` (
  `id` varchar(255) NOT NULL,
  `charger_type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKit7wx8xbm9u6iniy2ox45ydd1` (`location_id`),
  CONSTRAINT `FKit7wx8xbm9u6iniy2ox45ydd1` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `location` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `latitude` bigint DEFAULT NULL,
  `longitude` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ltij63d2oq8xj94dgd3rkyuuh` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `charging_point` (
  `id_charging` bigint NOT NULL AUTO_INCREMENT,
  `in_use` bit(1) DEFAULT NULL,
  `power_level` double DEFAULT NULL,
  `charging_station_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_charging`),
  KEY `FK4mwnorafwgpbarmg1hmpmglh9` (`charging_station_id`),
  CONSTRAINT `FK4mwnorafwgpbarmg1hmpmglh9` FOREIGN KEY (`charging_station_id`) REFERENCES `charging_station` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
