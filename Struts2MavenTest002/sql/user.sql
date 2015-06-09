CREATE TABLE `user` (
  `userid` varchar(10) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` char(128) DEFAULT NULL,
  `permission` char(2) DEFAULT NULL,
  `delflg` char(2) DEFAULT NULL,
  `lastupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
