
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car_model`;
CREATE TABLE `car_model` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'object unique id',
  `creator_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of creator user',
  `updater_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of updater user',
  `deleter_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of deleter user',
  `create_time` bigint(20) DEFAULT NULL COMMENT 'time when created(timestamp in second)',
  `update_time` bigint(20) DEFAULT NULL COMMENT 'time when updated(timestamp in second)',
  `delete_time` bigint(20) DEFAULT NULL COMMENT 'time when deleted(timestamp in sccond)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'a flag whether this object is deleted',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'car model name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO car_model(id, name) VALUES ('car_model_0', 'Toyota Camry');
INSERT INTO car_model(id, name) VALUES ('car_model_1', 'BMW 650');

