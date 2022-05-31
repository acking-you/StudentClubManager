/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : student_system

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 01/06/2022 01:49:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for club_member_relations
-- ----------------------------
DROP TABLE IF EXISTS `club_member_relations`;
CREATE TABLE `club_member_relations`  (
  `club_id` bigint(20) UNSIGNED NOT NULL,
  `club_member_id` bigint(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`club_member_id`, `club_id`) USING BTREE,
  INDEX `fk_club_member_relations_club`(`club_id`) USING BTREE,
  CONSTRAINT `fk_club_member_relations_club` FOREIGN KEY (`club_id`) REFERENCES `clubs` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_club_member_relations_club_member` FOREIGN KEY (`club_member_id`) REFERENCES `club_members` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
