/*
Navicat MySQL Data Transfer

Source Server         : 39.108.239.226
Source Server Version : 50716
Source Host           : 39.108.239.226:3306
Source Database       : cm_annual_convention

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-05-24 10:29:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cm_job_num
-- ----------------------------
DROP TABLE IF EXISTS `cm_job_num`;
CREATE TABLE `cm_job_num` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `job_num` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '工号',
  `del_flag` enum('0','1') COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标记(0=未删除;1=已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of cm_job_num
-- ----------------------------
INSERT INTO `cm_job_num` VALUES ('1', 'CM001', '0');
INSERT INTO `cm_job_num` VALUES ('2', 'CM002', '0');
INSERT INTO `cm_job_num` VALUES ('3', 'CM003', '0');
INSERT INTO `cm_job_num` VALUES ('4', 'CM004', '0');
INSERT INTO `cm_job_num` VALUES ('5', 'CM005', '0');
INSERT INTO `cm_job_num` VALUES ('6', 'CM006', '0');
INSERT INTO `cm_job_num` VALUES ('7', 'CM007', '0');
INSERT INTO `cm_job_num` VALUES ('8', 'CM008', '0');
INSERT INTO `cm_job_num` VALUES ('9', 'CM009', '0');
INSERT INTO `cm_job_num` VALUES ('10', 'CM010', '0');
INSERT INTO `cm_job_num` VALUES ('11', 'CM011', '0');
INSERT INTO `cm_job_num` VALUES ('12', 'CM012', '0');
INSERT INTO `cm_job_num` VALUES ('13', 'CM013', '0');
INSERT INTO `cm_job_num` VALUES ('14', 'CM014', '0');
INSERT INTO `cm_job_num` VALUES ('15', 'CM015', '0');
INSERT INTO `cm_job_num` VALUES ('16', 'CM016', '0');
INSERT INTO `cm_job_num` VALUES ('17', 'CM017', '0');
INSERT INTO `cm_job_num` VALUES ('18', 'CM018', '0');
INSERT INTO `cm_job_num` VALUES ('19', 'CM019', '0');
INSERT INTO `cm_job_num` VALUES ('20', 'CM020', '0');
INSERT INTO `cm_job_num` VALUES ('21', 'CM021', '0');
INSERT INTO `cm_job_num` VALUES ('22', 'CM022', '0');
INSERT INTO `cm_job_num` VALUES ('23', 'CM023', '0');
INSERT INTO `cm_job_num` VALUES ('24', 'CM024', '0');
INSERT INTO `cm_job_num` VALUES ('25', 'CM025', '0');
INSERT INTO `cm_job_num` VALUES ('26', 'CM026', '0');
INSERT INTO `cm_job_num` VALUES ('27', 'CM027', '0');
INSERT INTO `cm_job_num` VALUES ('28', 'CM028', '0');
INSERT INTO `cm_job_num` VALUES ('29', 'CM029', '0');
INSERT INTO `cm_job_num` VALUES ('30', 'CM030', '0');
INSERT INTO `cm_job_num` VALUES ('31', 'CM031', '0');
INSERT INTO `cm_job_num` VALUES ('32', 'CM032', '0');
INSERT INTO `cm_job_num` VALUES ('33', 'CM033', '0');
INSERT INTO `cm_job_num` VALUES ('34', 'CM034', '0');
INSERT INTO `cm_job_num` VALUES ('35', 'CM035', '0');
INSERT INTO `cm_job_num` VALUES ('36', 'CM036', '0');
INSERT INTO `cm_job_num` VALUES ('37', 'CM037', '0');
INSERT INTO `cm_job_num` VALUES ('38', 'CM038', '0');
INSERT INTO `cm_job_num` VALUES ('39', 'CM039', '0');
INSERT INTO `cm_job_num` VALUES ('40', 'CM040', '0');
INSERT INTO `cm_job_num` VALUES ('41', 'CM041', '0');
INSERT INTO `cm_job_num` VALUES ('42', 'CM042', '0');
INSERT INTO `cm_job_num` VALUES ('43', 'CM043', '0');
INSERT INTO `cm_job_num` VALUES ('44', 'CM044', '0');
INSERT INTO `cm_job_num` VALUES ('45', 'CM045', '0');
INSERT INTO `cm_job_num` VALUES ('46', 'CM046', '0');
INSERT INTO `cm_job_num` VALUES ('47', 'CM047', '0');
INSERT INTO `cm_job_num` VALUES ('48', 'CM048', '0');
INSERT INTO `cm_job_num` VALUES ('49', 'CM049', '0');
INSERT INTO `cm_job_num` VALUES ('50', 'CM050', '0');
INSERT INTO `cm_job_num` VALUES ('51', 'CM051', '0');
INSERT INTO `cm_job_num` VALUES ('52', 'CM052', '0');
INSERT INTO `cm_job_num` VALUES ('53', 'CM053', '0');
INSERT INTO `cm_job_num` VALUES ('54', 'CM054', '0');
INSERT INTO `cm_job_num` VALUES ('55', 'CM055', '0');
INSERT INTO `cm_job_num` VALUES ('56', 'CM056', '0');
INSERT INTO `cm_job_num` VALUES ('57', 'CM057', '0');
INSERT INTO `cm_job_num` VALUES ('58', 'CM058', '0');
INSERT INTO `cm_job_num` VALUES ('59', 'CM059', '0');
INSERT INTO `cm_job_num` VALUES ('60', 'CM060', '0');
INSERT INTO `cm_job_num` VALUES ('61', 'CM061', '0');
INSERT INTO `cm_job_num` VALUES ('62', 'CM062', '0');
INSERT INTO `cm_job_num` VALUES ('63', 'CM063', '0');
INSERT INTO `cm_job_num` VALUES ('64', 'CM064', '0');
INSERT INTO `cm_job_num` VALUES ('65', 'CM065', '0');
INSERT INTO `cm_job_num` VALUES ('66', 'CM066', '0');
INSERT INTO `cm_job_num` VALUES ('67', 'CM067', '0');
INSERT INTO `cm_job_num` VALUES ('68', 'CM068', '0');
INSERT INTO `cm_job_num` VALUES ('69', 'CM069', '0');
INSERT INTO `cm_job_num` VALUES ('70', 'CM070', '0');
INSERT INTO `cm_job_num` VALUES ('71', 'CM071', '0');
INSERT INTO `cm_job_num` VALUES ('72', 'CM072', '0');
INSERT INTO `cm_job_num` VALUES ('73', 'CM073', '0');
INSERT INTO `cm_job_num` VALUES ('74', 'CM074', '0');
INSERT INTO `cm_job_num` VALUES ('75', 'CM075', '0');
INSERT INTO `cm_job_num` VALUES ('76', 'CM076', '0');
INSERT INTO `cm_job_num` VALUES ('77', 'CM077', '0');
INSERT INTO `cm_job_num` VALUES ('78', 'CM078', '0');
INSERT INTO `cm_job_num` VALUES ('79', 'CM079', '0');
INSERT INTO `cm_job_num` VALUES ('80', 'CM080', '0');
INSERT INTO `cm_job_num` VALUES ('81', 'CM081', '0');
INSERT INTO `cm_job_num` VALUES ('82', 'CM082', '0');
INSERT INTO `cm_job_num` VALUES ('83', 'CM083', '0');
INSERT INTO `cm_job_num` VALUES ('84', 'CM084', '0');
INSERT INTO `cm_job_num` VALUES ('85', 'CM085', '0');
INSERT INTO `cm_job_num` VALUES ('86', 'CM086', '0');
INSERT INTO `cm_job_num` VALUES ('87', 'CM087', '0');
INSERT INTO `cm_job_num` VALUES ('88', 'CM088', '0');
INSERT INTO `cm_job_num` VALUES ('89', 'CM089', '0');
INSERT INTO `cm_job_num` VALUES ('90', 'CM090', '0');
INSERT INTO `cm_job_num` VALUES ('91', 'CM091', '0');
INSERT INTO `cm_job_num` VALUES ('92', 'CM092', '0');
INSERT INTO `cm_job_num` VALUES ('93', 'CM093', '0');
INSERT INTO `cm_job_num` VALUES ('94', 'CM094', '0');
INSERT INTO `cm_job_num` VALUES ('95', 'CM095', '0');
INSERT INTO `cm_job_num` VALUES ('96', 'CM096', '0');
INSERT INTO `cm_job_num` VALUES ('97', 'CM097', '0');
INSERT INTO `cm_job_num` VALUES ('98', 'CM098', '0');
INSERT INTO `cm_job_num` VALUES ('99', 'CM099', '0');
INSERT INTO `cm_job_num` VALUES ('100', 'CM100', '0');

-- ----------------------------
-- Table structure for cm_manager
-- ----------------------------
DROP TABLE IF EXISTS `cm_manager`;
CREATE TABLE `cm_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '管理员用户名',
  `user_password` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '管理员密码',
  `head_pic` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '管理员头像上传地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `del_flag` enum('0','1') COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标记(0=未删除;1=删除)',
  `remark` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of cm_manager
-- ----------------------------
INSERT INTO `cm_manager` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', null, '2017-12-11 16:52:00', '2017-12-11 16:52:00', null, '0', null);

-- ----------------------------
-- Table structure for cm_picture_config
-- ----------------------------
DROP TABLE IF EXISTS `cm_picture_config`;
CREATE TABLE `cm_picture_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pic_url` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片地址',
  `type` enum('2','1','0') COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '0 注册页图片 1年会表演节目表图片',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of cm_picture_config
-- ----------------------------

-- ----------------------------
-- Table structure for cm_prize
-- ----------------------------
DROP TABLE IF EXISTS `cm_prize`;
CREATE TABLE `cm_prize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `prize_level_id` bigint(20) DEFAULT NULL COMMENT '奖品等级id',
  `prize_name` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '奖品名称',
  `prize_num` bigint(20) DEFAULT NULL COMMENT '奖品数量',
  `prize_pic` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '奖品图片上传地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `del_flag` enum('0','1') CHARACTER SET utf8 DEFAULT '0' COMMENT '删除标记(0=未删除;1=已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of cm_prize
-- ----------------------------
INSERT INTO `cm_prize` VALUES ('1', '1', '小米电视49吋', null, 'https://img.joyami.com/upload/20180117/P00063db2ef759424f7cb7c9931d9b547721.jpg', '2018-01-17 10:53:29', '2018-01-17 10:53:29', null, '0');
INSERT INTO `cm_prize` VALUES ('2', '2', '米家扫地机器人', null, 'https://img.joyami.com/upload/20180117/P00079579cdea4044abfa22ae887a150b085.jpg', '2018-01-17 10:53:51', '2018-01-17 10:53:51', null, '0');
INSERT INTO `cm_prize` VALUES ('3', '3', '红米note4 4G内存版', null, 'https://img.joyami.com/upload/20180117/P000060b48c6330e403694f3ae6ab6b27a71.jpg', '2018-01-17 10:54:12', '2018-01-17 10:54:12', null, '0');
INSERT INTO `cm_prize` VALUES ('4', '4', '红米note4 3G内存版', null, 'https://img.joyami.com/upload/20180117/P000661d358bb4e5441f834c88c6ee4cbe66.jpg', '2018-01-17 10:54:28', '2018-01-17 10:54:28', null, '0');
INSERT INTO `cm_prize` VALUES ('5', '5', '米家电磁炉', null, 'https://img.joyami.com/upload/20180117/P000033185a5c39e40328eed694e9538fbf8.jpg', '2018-01-17 10:54:43', '2018-01-17 10:54:43', null, '0');

-- ----------------------------
-- Table structure for cm_prize_level
-- ----------------------------
DROP TABLE IF EXISTS `cm_prize_level`;
CREATE TABLE `cm_prize_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prize_level_name` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '奖品等级名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `del_flag` enum('0','1') CHARACTER SET utf8 DEFAULT '0' COMMENT '删除标记(0=未删除;1=删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of cm_prize_level
-- ----------------------------
INSERT INTO `cm_prize_level` VALUES ('1', '一等奖', '2018-01-17 10:52:49', null, '0');
INSERT INTO `cm_prize_level` VALUES ('2', '二等奖', '2018-01-17 10:52:54', null, '0');
INSERT INTO `cm_prize_level` VALUES ('3', '三等奖', '2018-01-17 10:52:59', null, '0');
INSERT INTO `cm_prize_level` VALUES ('4', '四等奖', '2018-01-17 10:53:04', null, '0');
INSERT INTO `cm_prize_level` VALUES ('5', '五等奖', '2018-01-17 10:53:09', null, '0');

-- ----------------------------
-- Table structure for cm_prize_record
-- ----------------------------
DROP TABLE IF EXISTS `cm_prize_record`;
CREATE TABLE `cm_prize_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `prize_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `del_flag` enum('0','1') COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标记(0=未删除;1=删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of cm_prize_record
-- ----------------------------
INSERT INTO `cm_prize_record` VALUES ('1', '4', '3', '2018-04-30 14:28:34', null, '0');
INSERT INTO `cm_prize_record` VALUES ('2', '6', '3', '2018-04-30 14:28:34', null, '0');
INSERT INTO `cm_prize_record` VALUES ('3', '8', '3', '2018-04-30 14:28:34', null, '0');
INSERT INTO `cm_prize_record` VALUES ('4', '9', '3', '2018-04-30 14:28:34', null, '0');
INSERT INTO `cm_prize_record` VALUES ('5', '10', '3', '2018-04-30 14:28:34', null, '0');
INSERT INTO `cm_prize_record` VALUES ('6', '3', '1', '2018-04-30 14:29:11', null, '0');
INSERT INTO `cm_prize_record` VALUES ('7', '5', '1', '2018-04-30 14:29:11', null, '0');

-- ----------------------------
-- Table structure for cm_users
-- ----------------------------
DROP TABLE IF EXISTS `cm_users`;
CREATE TABLE `cm_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_num` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '工号',
  `user_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '姓名',
  `user_nick_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '昵称',
  `head_pic` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '头像上传地址',
  `tel_num` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '电话号码',
  `address` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮寄地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `del_flag` enum('0','1') COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标记(0=未删除;1=删除)',
  `remark` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `wechat_number` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `job_num` (`job_num`),
  UNIQUE KEY `wechat_number` (`wechat_number`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of cm_users
-- ----------------------------
INSERT INTO `cm_users` VALUES ('1', 'CM001', '美人1', null, 'https://img.joyami.com/upload/20180105/P000629ba8debbd944979ea43a8f5837a5df.jpg', '110', '110', '2018-01-05 14:35:18', '2018-01-05 14:35:18', null, '0', null, null);
INSERT INTO `cm_users` VALUES ('2', 'CM002', '陈奕迅', null, 'https://img.joyami.com/upload/20180105/P000407c7aa91a684578b26cdb3877ccf715.jpg', '110', '110', '2018-01-05 14:35:38', '2018-01-05 14:35:38', null, '0', null, null);
INSERT INTO `cm_users` VALUES ('3', 'CM003', '美人2', null, 'https://img.joyami.com/upload/20180105/P0001046f89096b14812b70b3cda3a221164.jpg', '110', '110', '2018-01-05 14:36:14', '2018-01-05 14:36:14', null, '0', '1', null);
INSERT INTO `cm_users` VALUES ('4', 'CM004', '喵星人', null, 'https://img.joyami.com/upload/20180105/P00005ad80f7e9d341fea691007dd41d73c8.jpg', '110', '110', '2018-01-05 14:36:37', '2018-01-05 14:36:37', null, '0', '1', null);
INSERT INTO `cm_users` VALUES ('5', 'CM005', '美人3', null, 'https://img.joyami.com/upload/20180105/P0000094d85caaff40638b176772481cc639.jpg', '110', '110', '2018-01-05 14:37:11', '2018-01-05 14:37:11', null, '0', '1', null);
INSERT INTO `cm_users` VALUES ('6', 'CM006', '汪星人', null, 'https://img.joyami.com/upload/20180105/P000f9c7cb0abc604404a44c79a174a967da.jpg', '110', '110', '2018-01-05 14:37:32', '2018-01-05 14:37:32', null, '0', '1', null);
INSERT INTO `cm_users` VALUES ('7', 'CM007', '修睿', null, 'https://img.joyami.com/upload/20180105/P000b9337197f8304d46ae4ec9809768aa56.jpg', '110', '110', '2018-01-05 14:37:50', '2018-01-05 14:37:50', null, '0', null, null);
INSERT INTO `cm_users` VALUES ('8', 'CM008', '祥哥', null, 'https://img.joyami.com/upload/20180105/P000333f0c211ae045e9a62a9d7b69d65986.jpg', '110', '110', '2018-01-05 14:38:12', '2018-01-05 14:38:12', null, '0', '1', null);
INSERT INTO `cm_users` VALUES ('9', 'CM009', '1', null, 'https://img.joyami.com/upload/20180110/P000c8165f9d60a6466ea3cfd8bd29d889a4.jpg', '1', '1', '2018-01-10 00:09:25', '2018-01-10 00:09:25', null, '0', '1', null);
INSERT INTO `cm_users` VALUES ('10', 'CM010', '1', null, 'https://img.joyami.com/upload/20180110/P000249e2d35a0444111949dbb1f62746e98.jpg', '1', '1', '2018-01-10 00:09:48', '2018-01-10 00:09:48', null, '0', '1', null);
