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

 Date: 01/06/2022 01:48:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for club_members
-- ----------------------------
DROP TABLE IF EXISTS `club_members`;
CREATE TABLE `club_members`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `name` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `sex` enum('男','女') CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL DEFAULT '男',
  `age` bigint(20) UNSIGNED NOT NULL,
  `major` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `interest` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `student_id`(`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
