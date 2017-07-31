-- database ogn_fdk

--
-- 资源表(菜单表) 
--
DROP TABLE IF EXISTS `ogn_resource`;
CREATE TABLE `ogn_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `audit_flag` varchar(2) DEFAULT NULL COMMENT '审核状态(0-未审核，1-审核通过，2-审核不通过)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) DEFAULT 0 COMMENT '删除标志(0-正常，1-删除)',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `icon` varchar(512) DEFAULT NULL COMMENT '图标',
  `name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `order_no` int(11) DEFAULT NULL COMMENT '排序序号',
  `type` varchar(10) DEFAULT NULL COMMENT '资源类型',
  `url` varchar(100) DEFAULT NULL COMMENT '资源请求你路径',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `ogn_resource` (`id`,`audit_flag`,`create_date`,`delete_flag`,`update_date`,`icon`,`name`,`order_no`,`type`,`url`,`parent_id`) values 
(10,NULL,'2016-08-17 17:06:19','0','2016-10-28 17:02:57','','权限管理',10,'module','/user/list',NULL),
(11,NULL,'2016-09-07 15:15:58','0','2016-10-28 17:03:06','','用户列表',11,'page','/user/list','10'),
(12,NULL,'2016-09-07 15:17:37','0','2016-10-28 17:03:13','','角色列表',12,'page','/user/role_list','10'),
(13,NULL,'2016-09-18 16:34:14','0','2016-10-28 17:03:19','','菜单列表',13,'page','/user/menu_list','10'),
(14,NULL,'2017-03-28 16:37:07','0','2017-03-28 16:37:07','','换肤管理',100,'module','/user/skin/list',NULL),
(90,NULL,'2017-03-17 15:32:19','0','2017-03-17 15:32:19','','修改密码',90,'module','/user/update_pwd',NULL);

--
-- 角色
--
DROP TABLE IF EXISTS `ogn_role`;
CREATE TABLE `ogn_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `audit_flag` varchar(2) DEFAULT NULL COMMENT '审核状态(0-未审核，1-审核通过，2-审核不通过)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) DEFAULT 0 COMMENT '删除标志(0-正常，1-删除)',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `code` varchar(128) DEFAULT NULL COMMENT '角色编码',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `no` int(11) DEFAULT NULL COMMENT '主键',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `ogn_role` VALUES ('1', null, '2016-10-23 15:37:31', '0', '2017-03-28 16:37:35', null, '拥有所有的权限', '超级管理员', null, null, null);

--
-- 角色资源关系
--
DROP TABLE IF EXISTS `ogn_role_resource`;
CREATE TABLE `ogn_role_resource` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `resources_id` varchar(32) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`role_id`,`resources_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `ogn_role_resource` VALUES ('1', '10');
INSERT INTO `ogn_role_resource` VALUES ('1', '11');
INSERT INTO `ogn_role_resource` VALUES ('1', '12');
INSERT INTO `ogn_role_resource` VALUES ('1', '13');
INSERT INTO `ogn_role_resource` VALUES ('1', '14');
INSERT INTO `ogn_role_resource` VALUES ('1', '90');

--
-- 用户
--
DROP TABLE IF EXISTS `ogn_user`;
CREATE TABLE `ogn_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `audit_flag` varchar(2) DEFAULT NULL COMMENT '审核状态(0-未审核，1-审核通过，2-审核不通过)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) DEFAULT 0 COMMENT '删除标志(0-正常，1-删除)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `ogn_user` VALUES ('1', null, '2016-08-17 15:17:02', '0', '2017-03-28 17:55:54', null, '13800138000', 'E10ADC3949BA59ABBE56E057F20F883E', '超级管理员', '1', 'admin', '0', 'skin_blue');

--
-- 用户角色关系
--
DROP TABLE IF EXISTS `ogn_user_role`;
CREATE TABLE `ogn_user_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `ogn_user_role` VALUES ('1', '1');


