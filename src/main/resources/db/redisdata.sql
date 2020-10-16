/*
Navicat MySQL Data Transfer

Source Server         : Mzx
Source Server Version : 80021
Source Host           : 106.15.193.35:9527
Source Database       : redisdata

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2020-10-17 05:55:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `sid` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `sname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `sclass` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Z202014040209', '马章轩', '计科18102班');
INSERT INTO `user` VALUES ('2', '1234567891231', '小马哥', '计科18102班');
