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

 Date: 18/05/2023 22:56:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_product
-- ----------------------------
DROP TABLE IF EXISTS `demo_product`;
CREATE TABLE `demo_product`  (
  `creator_id` bigint NULL DEFAULT NULL,
  `updater_id` bigint NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NOT NULL,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `id` bigint NOT NULL,
  `status` tinyint NOT NULL DEFAULT 0,
  `marks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_product
-- ----------------------------
INSERT INTO `demo_product` VALUES (1067246875800000001, 1067246875800000001, '2023-05-08 16:02:42', '2023-05-08 20:20:09', '\\upload\\demo\\logo_2.png', 985.00, '测试2', 1655483350610685954, 0, '测试2');
INSERT INTO `demo_product` VALUES (1067246875800000001, NULL, '2023-05-08 16:42:46', NULL, '\\upload\\demo\\man.png', 15021.00, 'Pikachu', 1655493432685703170, 0, 'Pikapi pikachuuuu');
INSERT INTO `demo_product` VALUES (1067246875800000001, NULL, '2023-05-08 19:14:47', NULL, '\\upload\\demo\\logo.svg', 8808.00, '测试1', 1655531687820947458, 0, '测试1');

SET FOREIGN_KEY_CHECKS = 1;
