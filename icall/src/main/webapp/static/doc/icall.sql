DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` int(10) NOT NULL,
  `loginName` varchar(20) NOT NULL COMMENT '登陆名',
  `password` varchar(50) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `is_locked` char(1) DEFAULT '0' COMMENT '是否锁定，0未锁定，1锁定',
  `agent_id` varchar(20) DEFAULT NULL COMMENT '坐席工号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;