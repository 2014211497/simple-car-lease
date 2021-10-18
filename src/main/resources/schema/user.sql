
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'object unique id',
  `creator_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of creator user',
  `updater_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of updater user',
  `deleter_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id of deleter user',
  `create_time` bigint(20) DEFAULT NULL COMMENT 'time when created(timestamp in second)',
  `update_time` bigint(20) DEFAULT NULL COMMENT 'time when updated(timestamp in second)',
  `delete_time` bigint(20) DEFAULT NULL COMMENT 'time when deleted(timestamp in sccond)',
  `is_deleted` tinyint(4) DEFAULT 0 COMMENT 'a flag whether this object is deleted',
  `nickname` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'nickname ',
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'password(encrypted)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO user(id, nickname, password) VALUES ('LiLei', 'LiLei', '87d9bb400c0634691f0e3baaf1e2fd0d');
INSERT INTO user(id, nickname, password) VALUES ('HanMeimei', 'HanMeimei', '87d9bb400c0634691f0e3baaf1e2fd0d');
