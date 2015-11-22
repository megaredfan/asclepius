/*
Navicat MySQL Data Transfer

Source Server         : joseph
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : hospital

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-11-19 18:59:15
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
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`appdetailid`),
  KEY `hospitalid` (`hospitalid`),
  KEY `deptid` (`deptid`),
  KEY `doctorid` (`doctorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appdetail
-- ----------------------------
INSERT INTO `appdetail` VALUES ('1', '1', '1', '1', '2015-11-16 18:43:10', '2015-11-18 18:43:17', '2');
INSERT INTO `appdetail` VALUES ('41216998636', '41216998636', '41216998636', '41216998636', '2015-11-17 18:46:35', '2015-11-17 18:46:35', '0');

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `userid` bigint(11) DEFAULT NULL,
  `appointmentid` bigint(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `patientname` varchar(255) DEFAULT NULL,
  `patientsex` varchar(10) DEFAULT NULL,
  `patientage` int(3) DEFAULT NULL,
  `patientinsno` varchar(255) DEFAULT NULL,
  `appdetailid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`appointmentid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('41216998636', '41216998636', '2015-11-17 17:15:10', '0', null, null, '0', null, null);

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
-- Records of credit
-- ----------------------------
INSERT INTO `credit` VALUES ('41216998636', 'hello', '41216998636', '2015-11-17 17:15:10');

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
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('41216998636', '外科', '描述：外科', '41216998636');

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
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('41216998636', 'DR. Lee', '男', '主治医生', '41216998636', '41216998636', 'good');

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
-- Records of hospital
-- ----------------------------
INSERT INTO `hospital` VALUES ('41216998636', 'hopital1', 'good hospital');

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
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('41216998636', 'hello world!', 'visitor1', '2015-11-18 19:32:40', '0');
INSERT INTO `message` VALUES ('41216998637', 'hello world to you!', 'visitor2', '2015-11-18 19:32:40', '41216998636');

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
-- Records of sysadmin
-- ----------------------------
INSERT INTO `sysadmin` VALUES ('41216998636', '4QrcOUm6Wau+VuBX8g+IPg==', 'admin', '2015-11-17 17:15:11');

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('41216998636', '4QrcOUm6Wau+VuBX8g+IPg==', '2015-11-17 17:15:11', '2015-11-17 17:15:11', '500102199501178593', 'male', '熊纪元', 'megaredfan', '0');
INSERT INTO `user` VALUES ('73634463036', '4QrcOUm6Wau+VuBX8g+IPg==', '2015-11-15 18:16:00', null, '111111111111111111', '男', '邓迪', 'dd', '0');
