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

 Date: 18/05/2023 22:59:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `comment` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `sub_system_id` bigint NULL DEFAULT NULL COMMENT '租户编码',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_dept_id`(`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (1125415693534105602, '默认租户角色', '123', NULL, NULL, 1067246875800000001, '2019-10-21 15:59:50', 1650311204460670978, '2023-05-15 13:52:17');
INSERT INTO `system_role` VALUES (1657933874324881409, '系统管理员', '', NULL, 1001, 1067246875800000001, '2023-05-15 10:20:13', 1067246875800000001, '2023-05-15 10:20:13');

SET FOREIGN_KEY_CHECKS = 1;
