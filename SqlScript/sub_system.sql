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

 Date: 18/05/2023 22:59:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sub_system
-- ----------------------------
DROP TABLE IF EXISTS `sub_system`;
CREATE TABLE `sub_system`  (
  `id` bigint NOT NULL COMMENT 'id',
  `sub_system_id` bigint NULL DEFAULT NULL COMMENT '租户编码',
  `sub_system_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  `status` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '状态  0：停用    1：正常',
  `comment` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `user_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '登录账号ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `del_flag` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '删除标识 0：未删除    1：删除',
  `sub_system` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '系统租户   0：否   1：是',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_code`(`sub_system_id`) USING BTREE,
  INDEX `idx_create_date`(`create_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '租户管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sub_system
-- ----------------------------
INSERT INTO `sub_system` VALUES (1067246875800001000, 1001, '默认子系统', 1, NULL, 1067246875800000001, 'admin', 0, 1, NULL, '2019-10-21 15:59:47', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
