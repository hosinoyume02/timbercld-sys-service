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

 Date: 18/05/2023 22:59:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_language
-- ----------------------------
DROP TABLE IF EXISTS `system_language`;
CREATE TABLE `system_language`  (
  `table_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名',
  `table_id` bigint NOT NULL COMMENT '表主键',
  `field_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名',
  `field_value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段值',
  `language` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '语言',
  PRIMARY KEY (`table_name`, `table_id`, `field_name`, `language`) USING BTREE,
  INDEX `idx_table_id`(`table_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '国际化' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of system_language
-- ----------------------------
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000002, 'name', 'Authority Management', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000002, 'name', '权限管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000002, 'name', '權限管理', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000003, 'name', 'Add', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000003, 'name', '新增', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000003, 'name', '新增', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000004, 'name', 'Edit', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000004, 'name', '修改', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000004, 'name', '修改', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000005, 'name', 'Delete', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000005, 'name', '删除', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000005, 'name', '刪除', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000006, 'name', 'Export', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000006, 'name', '导出', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000006, 'name', '導出', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000007, 'name', 'Role Management', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000007, 'name', '角色管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000007, 'name', '角色管理', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000008, 'name', 'View', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000008, 'name', '查看', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000008, 'name', '查看', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000009, 'name', 'Add', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000009, 'name', '新增', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000009, 'name', '新增', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000010, 'name', 'Edit', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000010, 'name', '修改', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000010, 'name', '修改', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000011, 'name', 'Delete', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000011, 'name', '删除', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000011, 'name', '刪除', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000012, 'name', 'Department Management', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000012, 'name', '部门管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000012, 'name', '部門管理', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000014, 'name', 'View', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000014, 'name', '查看', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000014, 'name', '查看', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000015, 'name', 'Add', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000015, 'name', '新增', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000015, 'name', '新增', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000016, 'name', 'Edit', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000016, 'name', '修改', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000016, 'name', '修改', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000017, 'name', 'Delete', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000017, 'name', '删除', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000017, 'name', '刪除', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000025, 'name', 'Menu Management', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000025, 'name', '菜单管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000025, 'name', '菜單管理', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000026, 'name', 'View', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000026, 'name', '查看', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000026, 'name', '查看', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000027, 'name', 'Add', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000027, 'name', '新增', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000027, 'name', '新增', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000028, 'name', 'Edit', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000028, 'name', '修改', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000028, 'name', '修改', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000029, 'name', 'Delete', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000029, 'name', '删除', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000029, 'name', '刪除', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000030, 'name', 'Timed Task', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000030, 'name', '定时任务', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000030, 'name', '定時任務', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000031, 'name', 'View', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000031, 'name', '查看', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000031, 'name', '查看', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000032, 'name', 'Add', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000032, 'name', '新增', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000032, 'name', '新增', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000033, 'name', 'Edit', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000033, 'name', '修改', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000033, 'name', '修改', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000034, 'name', 'Delete', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000034, 'name', '删除', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000034, 'name', '刪除', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000035, 'name', 'Setting', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000035, 'name', '系统设置', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000035, 'name', '系統設置', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000036, 'name', 'Pause', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000036, 'name', '暂停', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000036, 'name', '暫停', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000037, 'name', 'Resume', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000037, 'name', '恢复', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000037, 'name', '恢復', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000038, 'name', 'Execute', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000038, 'name', '立即执行', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000038, 'name', '立即執行', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000039, 'name', 'Log List', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000039, 'name', '日志列表', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000039, 'name', '日誌列表', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000040, 'name', 'Parameter Management', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000040, 'name', '参数管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000040, 'name', '參數管理', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000046, 'name', 'Log Management', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000046, 'name', '日志管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000046, 'name', '日誌管理', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000048, 'name', 'Login Log', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000048, 'name', '登录日志', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000048, 'name', '登錄日誌', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000049, 'name', 'Operation Log', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000049, 'name', '操作日志', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000049, 'name', '操作日誌', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000050, 'name', 'Error Log', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000050, 'name', '异常日志', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000050, 'name', '異常日誌', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000051, 'name', 'SQL Monitoring', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000051, 'name', 'SQL监控', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000051, 'name', 'SQL監控', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000053, 'name', 'System Monitoring', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000053, 'name', '系统监控', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000053, 'name', '系統監控', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000055, 'name', 'User Management', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000055, 'name', '用户管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000055, 'name', '用戶管理', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000056, 'name', 'View', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000056, 'name', '查看', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000056, 'name', '查看', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000057, 'name', 'Add', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000057, 'name', '新增', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000057, 'name', '新增', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000058, 'name', 'Export', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000058, 'name', '导出', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000058, 'name', '導出', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000059, 'name', 'View', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000059, 'name', '查看', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000059, 'name', '查看', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000060, 'name', 'Edit', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000060, 'name', '修改', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000060, 'name', '修改', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000061, 'name', 'Delete', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000061, 'name', '删除', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1067246875800000061, 'name', '刪除', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1113375699198033921, 'name', 'SubSystem Management', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1113375699198033921, 'name', '子系统管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1113375699198033921, 'name', '子系統管理', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1113378406403162113, 'name', 'SubSystem Management', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1113378406403162113, 'name', '子系统用户', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1113378406403162113, 'name', '子系統用戶', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1114779542196600833, 'name', 'Tenant Role', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1114779542196600833, 'name', '子系统角色', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1114779542196600833, 'name', '子系統角色', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1189006068350906370, 'name', '信息流管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1189019113777197057, 'name', '信息流列表', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1189022065086623745, 'name', '已推荐信息', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1189022567878815745, 'name', '运营配置', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1189022673130680321, 'name', '数据统计', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1189022774020468737, 'name', '频道管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1189022926999318529, 'name', '地图推荐管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1189023018921684993, 'name', '信息流统计', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1189023106502946818, 'name', '留言统计', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981313, 'name', '热词表', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981313, 'name', '关键词管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981313, 'name', '热词表', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981314, 'name', 'View', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981314, 'name', '查看', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981314, 'name', '查看', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981315, 'name', 'Add', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981315, 'name', '新增', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981315, 'name', '新增', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981316, 'name', 'Edit', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981316, 'name', '修改', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981316, 'name', '修改', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981317, 'name', 'Delete', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981317, 'name', '删除', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981317, 'name', '刪除', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981318, 'name', 'Export', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981318, 'name', '导出', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1190105235395981318, 'name', '導出', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1216346571830505473, 'name', '平台数据统计', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1216346646417813505, 'name', '用户数据统计', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1216895263222312962, 'name', '测试权限菜单', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1432632388543254529, 'name', '新闻', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1607997375781277698, 'name', 'app管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1607997562524274689, 'name', '新闻管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1610191211475574785, 'name', '页面管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1613091623449960450, 'name', '消息管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1613338925828382721, 'name', '注销申请', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1613339824442613762, 'name', '频道管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1613339946064846849, 'name', '工具管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1613340081826078721, 'name', '用户管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1655388583889481730, 'name', 'Demo', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1655388583889481730, 'name', '功能示例', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1655388583889481730, 'name', '功能示例', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1655389242755919874, 'name', 'Product Management', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1655389242755919874, 'name', '商品管理', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1655389242755919874, 'name', '商品管理', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1655457251486916609, 'name', 'Add', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1655457251486916609, 'name', '新增', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1655457251486916609, 'name', '新增', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1655457440939433986, 'name', 'View', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1655457440939433986, 'name', '查看', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1655457440939433986, 'name', '查看', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1655457567359950850, 'name', 'Edit', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1655457567359950850, 'name', '修改', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1655457567359950850, 'name', '修改', 'zh-TW');
INSERT INTO `system_language` VALUES ('system_menu', 1655457676067921922, 'name', 'Delete', 'en-US');
INSERT INTO `system_language` VALUES ('system_menu', 1655457676067921922, 'name', '删除', 'zh-CN');
INSERT INTO `system_language` VALUES ('system_menu', 1655457676067921922, 'name', '刪除', 'zh-TW');

SET FOREIGN_KEY_CHECKS = 1;
