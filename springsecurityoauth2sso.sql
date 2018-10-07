/*
 Navicat Premium Data Transfer

 Source Server         : microideal
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost:3306
 Source Schema         : springsecurityoauth2sso

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 24/09/2018 21:40:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(11) NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, '权限设置', 0, 1, 0, 'authorizationSetting');
INSERT INTO `authority` VALUES (2, '用户管理', 1, 2, 1, '/usersManager');
INSERT INTO `authority` VALUES (3, '角色管理', 1, 3, 1, '/rolesManager');
INSERT INTO `authority` VALUES (4, '权限管理', 1, 4, 1, '/authoritiesManager');
INSERT INTO `authority` VALUES (5, '添加用户', 2, 5, 2, '/users/add');
INSERT INTO `authority` VALUES (6, '删除用户', 2, 6, 2, '/users/delete');
INSERT INTO `authority` VALUES (7, '分配用户的角色', 2, 7, 2, '/users/addUserRoles');
INSERT INTO `authority` VALUES (8, '添加角色', 3, 8, 2, '/roles/add');
INSERT INTO `authority` VALUES (9, '删除角色', 3, 9, 2, '/roles/delete');
INSERT INTO `authority` VALUES (10, '分配角色的权限', 3, 10, 2, '/roles/addRoleAuthorities');
INSERT INTO `authority` VALUES (11, '添加权限', 4, 11, 2, '/authorities/add');
INSERT INTO `authority` VALUES (12, '删除权限', 4, 12, 2, '/authorities/delete');
INSERT INTO `authority` VALUES (13, '编辑用户', 2, 13, 2, '/users/edit');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员');
INSERT INTO `role` VALUES (16, '会员系统');

-- ----------------------------
-- Table structure for role_authorities
-- ----------------------------
DROP TABLE IF EXISTS `role_authorities`;
CREATE TABLE `role_authorities`  (
  `role_id` bigint(20) NOT NULL,
  `authorities_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `authorities_id`) USING BTREE,
  INDEX `FK4cy1svku5asbuvksc7ap47iyi`(`authorities_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of role_authorities
-- ----------------------------
INSERT INTO `role_authorities` VALUES (1, 1);
INSERT INTO `role_authorities` VALUES (1, 2);
INSERT INTO `role_authorities` VALUES (1, 3);
INSERT INTO `role_authorities` VALUES (1, 4);
INSERT INTO `role_authorities` VALUES (1, 5);
INSERT INTO `role_authorities` VALUES (1, 6);
INSERT INTO `role_authorities` VALUES (1, 7);
INSERT INTO `role_authorities` VALUES (1, 8);
INSERT INTO `role_authorities` VALUES (1, 9);
INSERT INTO `role_authorities` VALUES (1, 10);
INSERT INTO `role_authorities` VALUES (1, 11);
INSERT INTO `role_authorities` VALUES (1, 12);
INSERT INTO `role_authorities` VALUES (1, 13);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL,
  `enable` bit(1) NULL DEFAULT NULL,
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `UK_sb8bbouer5wak8vyiiy4pf2bx`(`username`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, b'1', '$2a$10$S1d8RuS.hFEKKZ3l45UBSOdjDcA.Kg3RR5OzP.2tY70BiTVodSARC', 'microideal');
INSERT INTO `user` VALUES (17, b'1', '$2a$10$JtAe1eMnfuQJCoNabp3cD.uOokwVctDBFpQTXf4hQAmAeZm5b4Z0a', 'member');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `roles_id`) USING BTREE,
  INDEX `FKj9553ass9uctjrmh0gkqsmv0d`(`roles_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES (1, 1);
INSERT INTO `user_roles` VALUES (17, 16);

SET FOREIGN_KEY_CHECKS = 1;
