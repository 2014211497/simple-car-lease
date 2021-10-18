
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lease_order
-- ----------------------------
DROP TABLE IF EXISTS `lease_order`;
CREATE TABLE `lease_order` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'object unique id',
  `creator_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of creator user',
  `updater_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of updater user',
  `deleter_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of deleter user',
  `create_time` bigint(20) DEFAULT NULL COMMENT 'time when created(timestamp in second)',
  `update_time` bigint(20) DEFAULT NULL COMMENT 'time when updated(timestamp in second)',
  `delete_time` bigint(20) DEFAULT NULL COMMENT 'time when deleted(timestamp in sccond)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'a flag whether this object is deleted',
  `car_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'the target car id ',
  `begin_time` bigint(20) NOT NULL COMMENT 'time when this order would begin(timestamp in second)',
  `end_time` bigint(20) NOT NULL COMMENT 'time when this order would end(timestamp in second)',
  `is_finished` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'is this order finished',
  `is_canceled` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'is this order canceled',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
