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

 Date: 18/05/2023 22:59:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_role_data_scope
-- ----------------------------
DROP TABLE IF EXISTS `system_role_data_scope`;
CREATE TABLE `system_role_data_scope`  (
  `update_date` datetime NULL DEFAULT NULL,
  `updater_id` bigint NULL DEFAULT NULL,
  `id` bigint NOT NULL COMMENT 'id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色数据权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of system_role_data_scope
-- ----------------------------
INSERT INTO `system_role_data_scope` VALUES ('2023-05-15 10:20:16', 1067246875800000001, 1657933887843123202, 1657933874324881409, 1650310269189599233, 1067246875800000001, '2023-05-15 10:20:16');
INSERT INTO `system_role_data_scope` VALUES ('2023-05-15 10:20:16', 1067246875800000001, 1657933888082198530, 1657933874324881409, 1643948119193333762, 1067246875800000001, '2023-05-15 10:20:16');
INSERT INTO `system_role_data_scope` VALUES ('2023-05-15 10:24:50', 1650311204460670978, 1657935037686398977, 1657935027653623809, 1650310269189599233, 1650311204460670978, '2023-05-15 10:24:50');
INSERT INTO `system_role_data_scope` VALUES ('2023-05-15 10:24:50', 1650311204460670978, 1657935037917085698, 1657935027653623809, 1657934229070725121, 1650311204460670978, '2023-05-15 10:24:50');

SET FOREIGN_KEY_CHECKS = 1;
