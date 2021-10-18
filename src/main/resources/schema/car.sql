
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'object unique id',
  `creator_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of creator user',
  `updater_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of updater user',
  `deleter_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of deleter user',
  `create_time` bigint(20) DEFAULT NULL COMMENT 'time when created(timestamp in second)',
  `update_time` bigint(20) DEFAULT NULL COMMENT 'time when updated(timestamp in second)',
  `delete_time` bigint(20) DEFAULT NULL COMMENT 'time when deleted(timestamp in sccond)',
  `is_deleted` tinyint(4) DEFAULT 0 COMMENT 'a flag whether this object is deleted',
  `model_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'car model id',
  `number_plate` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'number plate of this car',
  `is_under_lease` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'is this car under lease',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_plate_index` (`number_plate`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO car(id, model_id, number_plate, is_under_lease) VALUES ('car_0', 'car_model_0', 'HK0000', 0);
INSERT INTO car(id, model_id, number_plate, is_under_lease) VALUES ('car_1', 'car_model_0', 'HK0001', 0);
INSERT INTO car(id, model_id, number_plate, is_under_lease) VALUES ('car_2', 'car_model_1', 'HK0002', 0);
INSERT INTO car(id, model_id, number_plate, is_under_lease) VALUES ('car_3', 'car_model_1', 'HK0003', 0);

