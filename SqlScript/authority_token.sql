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

 Date: 18/05/2023 22:55:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority_token
-- ----------------------------
DROP TABLE IF EXISTS `authority_token`;
CREATE TABLE `authority_token`  (
  `id` bigint NOT NULL COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户token',
  `expire_date` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_id`(`user_id`) USING BTREE,
  UNIQUE INDEX `uk_token`(`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户Token' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
