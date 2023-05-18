/*
 Navicat Premium Data Transfer

 Source Server         : 101.201.153.140_3306
 Source Server Type    : MySQL
 Source Server Version : 100306
 Source Host           : 101.201.153.140:3306
 Source Schema         : timbercld_sys_service

 Target Server Type    : MySQL
 Target Server Version : 100306
 File Encoding         : 65001

 Date: 18/05/2023 22:59:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_role_user
-- ----------------------------
DROP TABLE IF EXISTS `system_role_user`;
CREATE TABLE `system_role_user`  (
  `updater_id` bigint NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `id` bigint NOT NULL COMMENT 'id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色用户关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of system_role_user
-- ----------------------------
INSERT INTO `system_role_user` VALUES (1067246875800000001, '2023-05-15 10:20:41', 1657933994059677697, 1657933874324881409, 1650311204460670978, 1067246875800000001, '2023-05-15 10:20:41');
INSERT INTO `system_role_user` VALUES (1650311204460670978, '2023-05-15 10:28:29', 1657935955500777473, 1657935027653623809, 1657934522789445633, 1650311204460670978, '2023-05-15 10:28:29');
INSERT INTO `system_role_user` VALUES (1650311204460670978, '2023-05-15 11:14:11', 1657947455955165185, 1125415693534105602, 1657947455435071490, 1650311204460670978, '2023-05-15 11:14:11');
INSERT INTO `system_role_user` VALUES (1650311204460670978, '2023-05-15 13:47:55', 1657986145075093506, 1125415693534105602, 1657986144609525762, 1650311204460670978, '2023-05-15 13:47:55');

SET FOREIGN_KEY_CHECKS = 1;
