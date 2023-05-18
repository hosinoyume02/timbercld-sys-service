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

 Date: 18/05/2023 22:59:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `type` tinyint NOT NULL DEFAULT 0 COMMENT '0 后端 1前端',
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `head_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '性别   0：男   1：女    2：保密',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `super_admin` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '超级管理员   0：否   1：是',
  `super_sub_system` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '租户管理员   0：否   1：是',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态  0：停用   1：正常',
  `sub_system_id` bigint NULL DEFAULT 1001 COMMENT '租户编码',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE,
  INDEX `idx_create_date`(`create_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES (0, '1067246875800000001', 'admin', '$2a$10$012Kx2ba5jzqr9gLlG4MX.bnQJTD9UWqF57XDo2N3.fPtLne02u/m', '系统超级管理员', NULL, 0, 'root@renren.io', '13612345678', NULL, 1, 1, 1, 1001, 1067246875800000001, '2019-10-21 15:59:47', 1067246875800000001, '2019-10-21 15:59:47');
INSERT INTO `system_user` VALUES (0, '1650311204460670978', 'tccadmin', '$2a$10$tGJpET89KKukHnwp21omauAV9c/BSU8Q/7KHvU4HdDDlLv/JSvIC2', 'tccadmin', NULL, 0, 'tccadmin@tcc.com', '13869542135', 1650310269189599233, 0, 0, 1, 1001, 1067246875800000001, '2023-04-24 09:30:27', 1067246875800000001, '2023-05-15 10:20:41');
INSERT INTO `system_user` VALUES (0, '1657934522789445633', 'tcctest', '$2a$10$2sa9iwT1HPN9TgaTXM/LDueNpOvbngxu3Rg6xicqFvjCZlKQDzfHS', 'tcctest', NULL, 0, 'tcctest@timbercld.com', '13313131312', 1657934229070725121, 0, 0, 1, 1001, 1650311204460670978, '2023-05-15 10:22:47', 1650311204460670978, '2023-05-15 10:28:29');

SET FOREIGN_KEY_CHECKS = 1;
