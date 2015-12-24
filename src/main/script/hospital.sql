/*
Navicat MySQL Data Transfer

Source Server         : joseph
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : hospital

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-12-23 21:39:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for appdetail
-- ----------------------------
DROP TABLE IF EXISTS `appdetail`;
CREATE TABLE `appdetail` (
  `appdetailid` bigint(11) NOT NULL,
  `hospitalid` bigint(11) DEFAULT NULL,
  `deptid` bigint(11) DEFAULT NULL,
  `doctorid` bigint(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` varchar(22) DEFAULT NULL,
  `cost` float(10,2) DEFAULT '1.00',
  PRIMARY KEY (`appdetailid`),
  KEY `hospitalid` (`hospitalid`),
  KEY `deptid` (`deptid`),
  KEY `doctorid` (`doctorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `userid` bigint(11) DEFAULT NULL,
  `appointmentid` bigint(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT '1',
  `patientname` varchar(255) DEFAULT NULL,
  `patientsex` varchar(10) DEFAULT NULL,
  `patientage` int(3) DEFAULT NULL,
  `patientinsno` varchar(255) DEFAULT NULL,
  `cost` float(10,2) DEFAULT '0.00',
  `appdetailid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`appointmentid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for appointmentpool
-- ----------------------------
DROP TABLE IF EXISTS `appointmentpool`;
CREATE TABLE `appointmentpool` (
  `id` bigint(11) NOT NULL,
  `hospitalid` bigint(11) DEFAULT NULL,
  `departmentid` bigint(11) DEFAULT NULL,
  `doctorid` bigint(11) DEFAULT NULL,
  `day` varchar(10) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `amount` int(5) DEFAULT NULL,
  `cost` float(10,2) DEFAULT '1.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for credit
-- ----------------------------
DROP TABLE IF EXISTS `credit`;
CREATE TABLE `credit` (
  `creditid` bigint(11) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `userid` bigint(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`creditid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `deptid` bigint(11) NOT NULL,
  `deptname` varchar(255) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `hospitalid` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`deptid`),
  KEY `hospitalid` (`hospitalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `doctorid` bigint(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  `deptid` bigint(11) DEFAULT NULL,
  `hospitalid` bigint(11) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`doctorid`),
  KEY `deptid` (`deptid`),
  KEY `hospitalid` (`hospitalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital` (
  `hospitalid` bigint(11) NOT NULL,
  `hospitalname` varchar(255) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`hospitalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `pid` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `paymentid` bigint(11) NOT NULL,
  `cost` float(10,2) DEFAULT NULL,
  `userid` bigint(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `appointmentid` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`paymentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sysadmin
-- ----------------------------
DROP TABLE IF EXISTS `sysadmin`;
CREATE TABLE `sysadmin` (
  `adminid` bigint(11) NOT NULL,
  `password` varchar(1024) DEFAULT NULL,
  `adminname` varchar(255) DEFAULT NULL,
  `lastvisit` datetime DEFAULT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL,
  `password` varchar(1024) DEFAULT NULL,
  `regtime` datetime DEFAULT NULL,
  `lastlogin` datetime DEFAULT NULL,
  `idno` varchar(18) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `creditlevel` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `validatecode` varchar(1024) DEFAULT NULL,
  `activeflag` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into sysadmin(adminid,password,adminname) values(12345678910,'4QrcOUm6Wau+VuBX8g+IPg==','admin');
