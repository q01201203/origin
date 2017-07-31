/*
Navicat MySQL Data Transfer

Source Server         : lc
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : ogn_base

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-27 17:34:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ogn_app_constants
-- ----------------------------
DROP TABLE IF EXISTS `ogn_app_constants`;
CREATE TABLE `ogn_app_constants` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(4) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_app_constants
-- ----------------------------
INSERT INTO `ogn_app_constants` VALUES ('1', '2017-05-27 14:36:35', '2017-05-27 14:36:35', '1', 'version', '1.0', '0');
INSERT INTO `ogn_app_constants` VALUES ('2', '2017-05-27 14:45:20', '2017-05-27 14:45:20', '2', 'banner', 'http://img.hb.aicdn.com/5128b9dc208af92e20c6a89d225c0bc772cc0dc3d73d-dWfxOo_fw580', '0');
INSERT INTO `ogn_app_constants` VALUES ('3', '2017-05-27 14:45:50', '2017-05-27 14:45:50', '2', 'banner', 'http://img.hb.aicdn.com/f8519065c713d369adf5ee07f67db586fe274c6e5349-WD4lL2_fw658', '0');
INSERT INTO `ogn_app_constants` VALUES ('4', '2017-05-27 14:46:23', '2017-05-27 14:46:23', '2', 'headTip', 'http://pic74.nipic.com/file/20150805/18167868_163425124173_2.png', '0');
INSERT INTO `ogn_app_constants` VALUES ('5', '2017-05-27 14:46:55', '2017-05-27 14:46:55', '2', 'logo', 'http://a2.att.hudong.com/16/32/01300001153585129931327995154.jpg', '0');

-- ----------------------------
-- Table structure for ogn_app_feedback
-- ----------------------------
DROP TABLE IF EXISTS `ogn_app_feedback`;
CREATE TABLE `ogn_app_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` varchar(255) NOT NULL,
  `uid` int(11) NOT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_app_feedback
-- ----------------------------
INSERT INTO `ogn_app_feedback` VALUES ('1', '2017-05-25 14:19:49', '2017-05-25 14:19:49', '。。。。。。。。。啦啦啦啦啦啦啦啦啦', '3', '0');
INSERT INTO `ogn_app_feedback` VALUES ('2', '2017-05-25 14:30:25', '2017-05-25 14:30:25', 'sahldkhsafjdksla', '3', '0');
INSERT INTO `ogn_app_feedback` VALUES ('3', '2017-05-25 14:45:08', '2017-05-25 14:45:08', '您的每一个意见都是我们宝贵的财富', '3', '0');

-- ----------------------------
-- Table structure for ogn_app_guide
-- ----------------------------
DROP TABLE IF EXISTS `ogn_app_guide`;
CREATE TABLE `ogn_app_guide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `guide_name` varchar(255) NOT NULL,
  `guide_content` varchar(255) NOT NULL,
  `guide_type` int(4) NOT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_app_guide
-- ----------------------------
INSERT INTO `ogn_app_guide` VALUES ('2', '2017-05-26 11:48:48', '2017-05-26 11:48:48', '2、还是注册不了', '。。。', '1', '0');
INSERT INTO `ogn_app_guide` VALUES ('3', '2017-05-26 11:50:04', '2017-05-26 11:50:04', '1、什么是贷款', '。。。', '2', '0');
INSERT INTO `ogn_app_guide` VALUES ('4', '2017-05-26 11:50:46', '2017-05-26 11:50:46', '2、身份证信息变更如何处理', '！！！', '2', '0');
INSERT INTO `ogn_app_guide` VALUES ('7', '2017-05-26 15:03:23', '2017-05-26 15:03:23', '1、注册不了怎么办', '注册不了怎么办', '1', '0');
INSERT INTO `ogn_app_guide` VALUES ('8', '2017-05-26 15:05:52', '2017-05-26 15:05:52', '3、就是注册不了', '，，，', '1', '0');

-- ----------------------------
-- Table structure for ogn_app_money_detail
-- ----------------------------
DROP TABLE IF EXISTS `ogn_app_money_detail`;
CREATE TABLE `ogn_app_money_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `money` double(11,0) NOT NULL,
  `type` int(4) NOT NULL,
  `status` int(4) NOT NULL DEFAULT '1',
  `repay_way` int(4) DEFAULT NULL,
  `repay_time` datetime DEFAULT NULL,
  `repay_time_type` int(4) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_app_money_detail
-- ----------------------------
INSERT INTO `ogn_app_money_detail` VALUES ('1', '2017-05-24 15:44:25', '2017-05-24 15:44:35', '100', '1', '1', null, null, '1', '3', '0');
INSERT INTO `ogn_app_money_detail` VALUES ('2', '2017-05-24 15:49:08', '2017-05-24 15:49:10', '200', '1', '2', null, null, '1', '3', '0');
INSERT INTO `ogn_app_money_detail` VALUES ('3', '2017-05-24 15:50:47', '2017-05-24 15:50:50', '500', '1', '3', null, null, '1', '3', '0');
INSERT INTO `ogn_app_money_detail` VALUES ('4', '2017-05-25 09:52:06', '2017-05-25 09:52:06', '1', '1', '1', null, null, '2', '3', '0');
INSERT INTO `ogn_app_money_detail` VALUES ('5', '2017-05-24 15:58:44', '2017-05-24 15:58:48', '2000', '2', '4', '1', '2017-05-28 16:45:06', null, '3', '0');
INSERT INTO `ogn_app_money_detail` VALUES ('6', '2017-05-24 16:02:57', '2017-05-24 16:02:57', '200', '2', '5', '2', '2017-05-30 16:45:10', null, '3', '0');

-- ----------------------------
-- Table structure for ogn_app_person_detail
-- ----------------------------
DROP TABLE IF EXISTS `ogn_app_person_detail`;
CREATE TABLE `ogn_app_person_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `info_mobile` varchar(20) NOT NULL,
  `info_company_name` varchar(30) NOT NULL,
  `info_company_address` varchar(50) NOT NULL,
  `info_qq` varchar(20) NOT NULL,
  `info_weixin` varchar(30) NOT NULL,
  `info_home` varchar(50) NOT NULL,
  `info_emycontact_relation` int(4) NOT NULL,
  `info_emycontact_mobile` varchar(20) NOT NULL,
  `info_contact_relation` int(4) NOT NULL,
  `info_contact_mobile` varchar(20) NOT NULL,
  `uid` int(11) NOT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_app_person_detail
-- ----------------------------
INSERT INTO `ogn_app_person_detail` VALUES ('1', '2017-05-25 11:20:37', '2017-05-25 11:20:37', '123123', '123213', '132132', '312132', '132123', '123123', '1', '1231231', '1', '123123', '6', '0');

-- ----------------------------
-- Table structure for ogn_app_stu_detail
-- ----------------------------
DROP TABLE IF EXISTS `ogn_app_stu_detail`;
CREATE TABLE `ogn_app_stu_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `info_mobile` varchar(20) NOT NULL,
  `info_school` varchar(32) NOT NULL,
  `info_department` varchar(32) NOT NULL,
  `info_class` varchar(20) NOT NULL,
  `info_roomnumber` varchar(50) NOT NULL,
  `info_emycontact_relation` int(4) NOT NULL,
  `info_emycontact_mobile` varchar(20) NOT NULL,
  `info_contact_relation` int(4) NOT NULL,
  `info_contact_mobile` varchar(20) NOT NULL,
  `uid` int(11) NOT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_app_stu_detail
-- ----------------------------
INSERT INTO `ogn_app_stu_detail` VALUES ('1', '2017-05-25 11:20:00', '2017-05-25 11:20:00', '23123', '234234', '324234', '234234', '234234234', '1', '231321', '1', '2313', '3', '0');

-- ----------------------------
-- Table structure for ogn_app_task
-- ----------------------------
DROP TABLE IF EXISTS `ogn_app_task`;
CREATE TABLE `ogn_app_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `task_name` varchar(30) NOT NULL,
  `task_number` int(4) NOT NULL,
  `task_type` int(4) NOT NULL,
  `task_money` double(10,0) NOT NULL,
  `task_img` varchar(100) DEFAULT NULL,
  `task_hot` int(4) NOT NULL DEFAULT '0',
  `task_startTime` datetime NOT NULL,
  `task_endTime` datetime NOT NULL,
  `task_simpleStep` varchar(255) DEFAULT NULL,
  `task_detailedStep` varchar(120) DEFAULT NULL,
  `task_link` varchar(100) DEFAULT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_app_task
-- ----------------------------
INSERT INTO `ogn_app_task` VALUES ('1', '2017-05-22 15:00:19', '2017-05-22 15:00:19', 'F4', '5', '1', '100', 'http://imgsrc.baidu.com/baike/pic/item/aa18972bd40735faa844eaa09d510fb30f240876.jpg', '0', '2017-05-27 11:20:33', '2017-06-03 11:20:40', 'sdfdd', 'http://c.xiumi.us/board/v5/2ja1S/29187002', 'www.baidu.com', '0');
INSERT INTO `ogn_app_task` VALUES ('2', '2017-05-22 15:00:46', '2017-05-22 15:00:46', '三狼', '3', '1', '150', 'http://imgsrc.baidu.com/baike/pic/item/aa18972bd40735faa844eaa09d510fb30f240876.jpg', '0', '2017-05-31 11:20:45', '2017-06-01 11:20:50', 'sdfdd', 'http://c.xiumi.us/board/v5/2ja1S/29187002', 'www.baidu.com', '0');
INSERT INTO `ogn_app_task` VALUES ('3', '2017-05-22 15:01:07', '2017-05-22 15:01:07', '红buff', '8', '2', '300', 'http://imgsrc.baidu.com/baike/pic/item/aa18972bd40735faa844eaa09d510fb30f240876.jpg', '0', '2017-05-26 11:20:54', '2017-06-01 11:20:59', 'sdaa', 'http://c.xiumi.us/board/v5/2ja1S/29187002', 'www.baidu.com', '0');
INSERT INTO `ogn_app_task` VALUES ('4', '2017-05-22 15:01:39', '2017-05-22 15:01:39', '小龙', '0', '3', '500', 'http://imgsrc.baidu.com/baike/pic/item/aa18972bd40735faa844eaa09d510fb30f240876.jpg', '1', '2017-06-04 11:21:01', '2017-05-27 14:00:00', 'sdfsdf', 'https://c.xiumi.us/board/v5/2ja1S/29187002', 'www.baidu.com', '0');
INSERT INTO `ogn_app_task` VALUES ('5', '2017-05-27 10:40:05', '2017-05-27 10:40:05', 'f6', '0', '2', '200', 'http://imgsrc.baidu.com/baike/pic/item/aa18972bd40735faa844eaa09d510fb30f240876.jpg', '1', '2017-06-03 11:21:07', '2017-06-04 11:21:10', 'sdfsa', 'https://blog.csdn.net/stven_king/article/details/37814805', 'https://www.baidu.com', '0');
INSERT INTO `ogn_app_task` VALUES ('6', '2017-05-27 10:41:39', '2017-05-27 10:41:39', '123', '10', '3', '100', 'http://imgsrc.baidu.com/baike/pic/item/aa18972bd40735faa844eaa09d510fb30f240876.jpg', '1', '2017-07-27 11:21:14', '2017-09-30 11:21:19', 'asdfs', 'https://zhidao.baidu.com/question/564118674063365924.html', 'https://www.baidu.com', '0');

-- ----------------------------
-- Table structure for ogn_app_user
-- ----------------------------
DROP TABLE IF EXISTS `ogn_app_user`;
CREATE TABLE `ogn_app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mobile` varchar(20) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `pay_pwd` varchar(10) DEFAULT NULL,
  `balance` double(10,0) NOT NULL DEFAULT '0',
  `authority` int(4) NOT NULL DEFAULT '100',
  `money_max` double(10,0) NOT NULL DEFAULT '2000',
  `alipay_username` varchar(20) DEFAULT NULL,
  `alipay_useraccout` varchar(32) DEFAULT NULL,
  `img_face` varchar(100) DEFAULT NULL,
  `img_id_front` varchar(100) DEFAULT NULL,
  `img_id_back` varchar(100) DEFAULT NULL,
  `user_id_name` varchar(20) DEFAULT NULL,
  `user_id_number` varchar(30) DEFAULT NULL,
  `img_portrait` varchar(100) DEFAULT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `category` int(4) DEFAULT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_app_user
-- ----------------------------
INSERT INTO `ogn_app_user` VALUES ('2', '2017-05-19 15:01:50', '2017-05-23 14:26:14', '13611112222', '8A036D846CFD199BF31C5D90366F42CA', null, '300', '100', '2000', '13550990375', '121212', null, null, null, null, null, null, null, null, '0');
INSERT INTO `ogn_app_user` VALUES ('3', '2017-05-16 16:03:51', '2017-05-24 14:35:26', '13611114444', 'C33367701511B4F6020EC61DED352059', null, '400', '101', '3000', '2222222', '2222221', '123456', 'http://imgsrc.baidu.com/baike/pic/item/aa18972bd40735faa844eaa09d510fb30f240876.jpg', 'http://img5.duitang.com/uploads/item/201510/30/20151030171633_54wVe.jpeg', '王小二', '5112011202032020', 'http://imgsrc.baidu.com/baike/pic/item/aa18972bd40735faa844eaa09d510fb30f240876.jpg', '橘子酱萝莉', '1', '0');
INSERT INTO `ogn_app_user` VALUES ('5', '2017-05-23 10:12:09', '2017-05-23 10:12:09', '13611113333', 'E10ADC3949BA59ABBE56E057F20F883E', null, '100', '100', '2000', null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `ogn_app_user` VALUES ('6', '2017-05-23 16:06:40', '2017-05-23 16:06:40', '13612345612', 'E10ADC3949BA59ABBE56E057F20F883E', null, '500', '100', '2000', 'zwk', 'zwk', null, null, null, null, null, null, null, '2', '0');

-- ----------------------------
-- Table structure for ogn_app_user_bank
-- ----------------------------
DROP TABLE IF EXISTS `ogn_app_user_bank`;
CREATE TABLE `ogn_app_user_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bank_name` varchar(40) NOT NULL,
  `bank_number` int(40) NOT NULL,
  `bank_type` int(4) NOT NULL,
  `bank_mobile` varchar(20) NOT NULL,
  `uid` int(11) NOT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_app_user_bank
-- ----------------------------
INSERT INTO `ogn_app_user_bank` VALUES ('1', '2017-05-22 11:46:05', '2017-05-22 11:46:05', '工商银行', '516132123', '0', '13251513212', '3', '0');

-- ----------------------------
-- Table structure for ogn_app_user_task
-- ----------------------------
DROP TABLE IF EXISTS `ogn_app_user_task`;
CREATE TABLE `ogn_app_user_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  `auditTime` datetime DEFAULT NULL,
  `status` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_app_user_task
-- ----------------------------
INSERT INTO `ogn_app_user_task` VALUES ('1', '3', '1', '2017-05-27 14:01:26', '1');
INSERT INTO `ogn_app_user_task` VALUES ('2', '5', '1', '2017-05-27 13:01:30', '1');
INSERT INTO `ogn_app_user_task` VALUES ('3', '3', '2', null, '0');
INSERT INTO `ogn_app_user_task` VALUES ('4', '6', '4', '2017-05-27 16:01:35', '1');
INSERT INTO `ogn_app_user_task` VALUES ('5', '6', '3', null, '0');
INSERT INTO `ogn_app_user_task` VALUES ('6', '3', '4', '2017-05-27 16:01:39', '2');
INSERT INTO `ogn_app_user_task` VALUES ('7', '3', '3', '2017-05-27 15:59:36', '1');
INSERT INTO `ogn_app_user_task` VALUES ('8', '2', '1', null, '0');
INSERT INTO `ogn_app_user_task` VALUES ('9', '2', '2', null, '0');
INSERT INTO `ogn_app_user_task` VALUES ('10', '2', '3', '2017-05-27 14:02:59', '1');
INSERT INTO `ogn_app_user_task` VALUES ('11', '2', '4', null, '0');

-- ----------------------------
-- Table structure for ogn_app_validcode
-- ----------------------------
DROP TABLE IF EXISTS `ogn_app_validcode`;
CREATE TABLE `ogn_app_validcode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mobile` varchar(20) NOT NULL,
  `validcode` varchar(10) NOT NULL,
  `type` int(4) NOT NULL DEFAULT '0',
  `count` int(11) NOT NULL DEFAULT '0',
  `status` int(4) NOT NULL DEFAULT '1',
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_app_validcode
-- ----------------------------
INSERT INTO `ogn_app_validcode` VALUES ('7', '2017-05-16 16:01:11', '2017-05-22 11:09:56', '13611114444', '123456', '1', '0', '0', '0');
INSERT INTO `ogn_app_validcode` VALUES ('11', '2017-05-17 09:47:54', '2017-05-22 11:09:56', '13611114445', '123456', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('12', '2017-05-17 09:48:03', '2017-05-22 11:09:56', '13611114445', '123456', '1', '0', '0', '0');
INSERT INTO `ogn_app_validcode` VALUES ('13', '2017-05-23 10:56:00', '2017-05-23 10:56:00', '13611112222', '110110', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('14', '2017-05-23 11:17:54', '2017-05-23 11:17:54', '13611112223', '110110', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('15', '2017-05-23 11:35:22', '2017-05-23 11:35:22', '13611115555', '110110', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('16', '2017-05-23 11:38:40', '2017-05-23 11:38:40', '13633133131', '110110', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('17', '2017-05-23 11:38:40', '2017-05-23 11:38:40', '13633133131', '110110', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('18', '2017-05-23 11:40:17', '2017-05-23 11:40:17', '13611115555', '110110', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('19', '2017-05-23 11:49:01', '2017-05-23 11:49:01', '13611112222', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('20', '2017-05-23 11:49:02', '2017-05-23 11:49:02', '13611112222', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('21', '2017-05-23 11:56:30', '2017-05-23 11:56:30', '13611112222', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('22', '2017-05-23 11:59:27', '2017-05-23 11:59:27', '13611112222', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('23', '2017-05-23 14:15:17', '2017-05-23 14:15:17', '13611112222', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('24', '2017-05-23 14:16:33', '2017-05-23 14:16:33', '13611112222', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('25', '2017-05-23 14:16:41', '2017-05-23 14:16:41', '13611112222', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('26', '2017-05-23 14:20:12', '2017-05-23 14:20:12', '13611112222', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('27', '2017-05-23 14:22:46', '2017-05-23 14:22:46', '13611112222', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('28', '2017-05-23 14:24:17', '2017-05-23 14:24:17', '13611112222', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('29', '2017-05-23 14:26:03', '2017-05-23 14:26:03', '13611112222', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('30', '2017-05-23 14:31:18', '2017-05-23 14:31:18', '13611111111', '110110', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('31', '2017-05-23 14:39:11', '2017-05-23 14:39:11', '13611111111', '110110', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('32', '2017-05-23 15:11:08', '2017-05-23 15:11:08', '13611111111', '110110', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('33', '2017-05-23 16:05:34', '2017-05-23 16:05:34', '13612345612', '110110', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('34', '2017-05-25 17:29:14', '2017-05-25 17:29:14', '13612345612', '110110', '2', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('35', '2017-05-25 17:29:48', '2017-05-25 17:29:48', '13612313212', '110110', '1', '0', '1', '0');
INSERT INTO `ogn_app_validcode` VALUES ('36', '2017-05-25 17:37:16', '2017-05-25 17:37:16', '13612312312', '110110', '1', '0', '1', '0');

-- ----------------------------
-- Table structure for ogn_resource
-- ----------------------------
DROP TABLE IF EXISTS `ogn_resource`;
CREATE TABLE `ogn_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `audit_flag` varchar(2) DEFAULT NULL COMMENT '审核状态(0-未审核，1-审核通过，2-审核不通过)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) DEFAULT '0' COMMENT '删除标志(0-正常，1-删除)',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `icon` varchar(512) DEFAULT NULL COMMENT '图标',
  `name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `order_no` int(11) DEFAULT NULL COMMENT '排序序号',
  `type` varchar(10) DEFAULT NULL COMMENT '资源类型',
  `url` varchar(100) DEFAULT NULL COMMENT '资源请求你路径',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_resource
-- ----------------------------
INSERT INTO `ogn_resource` VALUES ('10', null, '2016-08-17 17:06:19', '0', '2016-10-28 17:02:57', '', '权限管理', '1', 'module', '/user/list', null);
INSERT INTO `ogn_resource` VALUES ('11', null, '2016-09-07 15:15:58', '0', '2016-10-28 17:03:06', '', '用户列表', '2', 'page', '/user/list', '10');
INSERT INTO `ogn_resource` VALUES ('12', null, '2016-09-07 15:17:37', '0', '2016-10-28 17:03:13', '', '角色列表', '3', 'page', '/user/role_list', '10');
INSERT INTO `ogn_resource` VALUES ('13', null, '2016-09-18 16:34:14', '0', '2016-10-28 17:03:19', '', '菜单列表', '4', 'page', '/user/menu_list', '10');
INSERT INTO `ogn_resource` VALUES ('14', null, '2017-03-28 16:37:07', '0', '2017-03-28 16:37:07', '', '换肤管理', '6', 'module', '/user/skin/list', null);
INSERT INTO `ogn_resource` VALUES ('90', null, '2017-03-17 15:32:19', '0', '2017-03-17 15:32:19', '', '修改密码', '5', 'module', '/user/update_pwd', null);
INSERT INTO `ogn_resource` VALUES ('94', null, null, '0', null, '&#xf02a4;', '用户管理', '20', 'module', '/admin/appUser/list', null);
INSERT INTO `ogn_resource` VALUES ('95', null, null, '0', null, null, 'App用户列表', '21', 'page', '/admin/appUser/user/list', '94');
INSERT INTO `ogn_resource` VALUES ('96', null, null, '0', null, null, '测试', '100', 'module', '/test/list', null);
INSERT INTO `ogn_resource` VALUES ('97', null, null, '0', null, null, '借款列表', '22', 'page', '/admin/appUser/getMoney/list?type=1', '94');
INSERT INTO `ogn_resource` VALUES ('98', null, null, '0', null, null, '任务管理', '10', 'module', '/admin/appTask/list', null);
INSERT INTO `ogn_resource` VALUES ('99', null, null, '0', null, null, '任务列表', '11', 'page', '/admin/appTask/list', '98');
INSERT INTO `ogn_resource` VALUES ('100', null, null, '0', null, null, '任务记录', '12', 'page', '/admin/appTask/userTask/list', '98');
INSERT INTO `ogn_resource` VALUES ('101', null, null, '0', null, null, '还款列表', '23', 'page', '/admin/appUser/getMoney/list?type=2', '94');

-- ----------------------------
-- Table structure for ogn_role
-- ----------------------------
DROP TABLE IF EXISTS `ogn_role`;
CREATE TABLE `ogn_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `audit_flag` varchar(2) DEFAULT NULL COMMENT '审核状态(0-未审核，1-审核通过，2-审核不通过)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) DEFAULT '0' COMMENT '删除标志(0-正常，1-删除)',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `code` varchar(128) DEFAULT NULL COMMENT '角色编码',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `no` int(11) DEFAULT NULL COMMENT '主键',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_role
-- ----------------------------
INSERT INTO `ogn_role` VALUES ('1', null, '2016-10-23 15:37:31', '0', '2017-03-28 16:37:35', null, '拥有所有的权限', '超级管理员', null, null, null);
INSERT INTO `ogn_role` VALUES ('2', null, null, '0', null, null, null, '小垃圾', null, null, null);

-- ----------------------------
-- Table structure for ogn_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `ogn_role_resource`;
CREATE TABLE `ogn_role_resource` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `resources_id` varchar(32) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`role_id`,`resources_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_role_resource
-- ----------------------------
INSERT INTO `ogn_role_resource` VALUES ('1', '10');
INSERT INTO `ogn_role_resource` VALUES ('1', '100');
INSERT INTO `ogn_role_resource` VALUES ('1', '101');
INSERT INTO `ogn_role_resource` VALUES ('1', '11');
INSERT INTO `ogn_role_resource` VALUES ('1', '12');
INSERT INTO `ogn_role_resource` VALUES ('1', '13');
INSERT INTO `ogn_role_resource` VALUES ('1', '14');
INSERT INTO `ogn_role_resource` VALUES ('1', '90');
INSERT INTO `ogn_role_resource` VALUES ('1', '94');
INSERT INTO `ogn_role_resource` VALUES ('1', '95');
INSERT INTO `ogn_role_resource` VALUES ('1', '96');
INSERT INTO `ogn_role_resource` VALUES ('1', '97');
INSERT INTO `ogn_role_resource` VALUES ('1', '98');
INSERT INTO `ogn_role_resource` VALUES ('1', '99');
INSERT INTO `ogn_role_resource` VALUES ('2', '14');
INSERT INTO `ogn_role_resource` VALUES ('2', '90');

-- ----------------------------
-- Table structure for ogn_test
-- ----------------------------
DROP TABLE IF EXISTS `ogn_test`;
CREATE TABLE `ogn_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sss` varchar(255) DEFAULT NULL,
  `df` varchar(255) DEFAULT NULL,
  `dfs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_test
-- ----------------------------
INSERT INTO `ogn_test` VALUES ('1', 'dfg', 'df', 'df');
INSERT INTO `ogn_test` VALUES ('2', 'sdf', 'sd', 'fs');

-- ----------------------------
-- Table structure for ogn_user
-- ----------------------------
DROP TABLE IF EXISTS `ogn_user`;
CREATE TABLE `ogn_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `audit_flag` varchar(2) DEFAULT NULL COMMENT '审核状态(0-未审核，1-审核通过，2-审核不通过)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) DEFAULT '0' COMMENT '删除标志(0-正常，1-删除)',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱地址',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `type` int(11) DEFAULT '0' COMMENT '用户类型',
  `current_skin` varchar(16) DEFAULT NULL COMMENT '当前主题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_user
-- ----------------------------
INSERT INTO `ogn_user` VALUES ('1', null, '2016-08-17 15:17:02', '0', '2017-03-28 17:55:54', null, '13800138000', 'E10ADC3949BA59ABBE56E057F20F883E', '超级管理员', '1', 'admin', '0', 'skin_red');
INSERT INTO `ogn_user` VALUES ('3', null, null, '0', null, null, '13285654252', 'E10ADC3949BA59ABBE56E057F20F883E', 'a', '1', 'aaaaaa', '0', null);
INSERT INTO `ogn_user` VALUES ('4', null, null, '0', null, null, '13254512211', 'F59BD65F7EDAFB087A81D4DCA06C4910', 'a', '1', 'q01201203', '0', null);
INSERT INTO `ogn_user` VALUES ('5', null, null, '0', null, null, '15241256325', 'F59BD65F7EDAFB087A81D4DCA06C4910', '121', '1', 'qqqqqq', '0', null);
INSERT INTO `ogn_user` VALUES ('6', null, '2017-05-09 18:02:44', '0', '2017-05-09 18:02:44', null, '13524256325', 'F59BD65F7EDAFB087A81D4DCA06C4910', '四级联考', '1', '1111111', '0', null);

-- ----------------------------
-- Table structure for ogn_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ogn_user_role`;
CREATE TABLE `ogn_user_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ogn_user_role
-- ----------------------------
INSERT INTO `ogn_user_role` VALUES ('1', '1');
INSERT INTO `ogn_user_role` VALUES ('3', '2');
INSERT INTO `ogn_user_role` VALUES ('4', '2');
INSERT INTO `ogn_user_role` VALUES ('5', '2');
INSERT INTO `ogn_user_role` VALUES ('6', '2');

-- ----------------------------
-- Procedure structure for finishTask
-- ----------------------------
DROP PROCEDURE IF EXISTS `finishTask`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `finishTask`(IN uid INT,IN tid INT)
BEGIN
	UPDATE ogn_app_user_task 
	SET status = 1 ,auditTime = NOW()
	WHERE ogn_app_user_task.uid = uid AND ogn_app_user_task.tid = tid;

	UPDATE ogn_app_task
	SET task_number = task_number -1
	WHERE ogn_app_task.id = tid;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for updateBalance
-- ----------------------------
DROP PROCEDURE IF EXISTS `updateBalance`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateBalance`(IN `uid` int)
BEGIN
	#Routine body goes here...
	UPDATE
	ogn_app_user
	SET ogn_app_user.balance = (SELECT SUM(task_money) FROM ogn_app_task,ogn_app_user_task
	WHERE ogn_app_user_task.tid = ogn_app_task.id AND ogn_app_user_task.`status` = 1 AND ogn_app_user_task.uid = uid)
	WHERE ogn_app_user.id = uid;
END
;;
DELIMITER ;
