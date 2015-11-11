/*
Navicat MySQL Data Transfer

Source Server         : joseph
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : hospital

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-11-11 10:16:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for appdetail
-- ----------------------------
DROP TABLE IF EXISTS `appdetail`;
CREATE TABLE `appdetail` (
  `appdetailid` int(11) NOT NULL,
  `hospitalid` int(11) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `doctorid` int(11) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `appointmentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`appdetailid`),
  KEY `appointmentid` (`appointmentid`),
  KEY `hospitalid` (`hospitalid`),
  KEY `deptid` (`deptid`),
  KEY `doctorid` (`doctorid`),
  CONSTRAINT `appdetail_ibfk_1` FOREIGN KEY (`appointmentid`) REFERENCES `appointment` (`appointmentid`),
  CONSTRAINT `appdetail_ibfk_2` FOREIGN KEY (`hospitalid`) REFERENCES `hospital` (`hospitalid`),
  CONSTRAINT `appdetail_ibfk_3` FOREIGN KEY (`deptid`) REFERENCES `department` (`deptid`),
  CONSTRAINT `appdetail_ibfk_4` FOREIGN KEY (`doctorid`) REFERENCES `doctor` (`doctorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `userid` int(11) DEFAULT NULL,
  `appointmentid` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `patientname` varchar(255) DEFAULT NULL,
  `patientsex` varchar(10) DEFAULT NULL,
  `patientage` int(3) DEFAULT NULL,
  `patientinsno` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`appointmentid`),
  KEY `userid` (`userid`),
  CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for credit
-- ----------------------------
DROP TABLE IF EXISTS `credit`;
CREATE TABLE `credit` (
  `creditid` int(11) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`creditid`),
  KEY `userid` (`userid`),
  CONSTRAINT `credit_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `deptid` int(11) NOT NULL,
  `deptname` varchar(255) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `hospitalid` int(11) DEFAULT NULL,
  PRIMARY KEY (`deptid`),
  KEY `hospitalid` (`hospitalid`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`hospitalid`) REFERENCES `hospital` (`hospitalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `doctorid` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `hospitalid` int(11) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`doctorid`),
  KEY `deptid` (`deptid`),
  KEY `hospitalid` (`hospitalid`),
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`deptid`) REFERENCES `department` (`deptid`),
  CONSTRAINT `doctor_ibfk_2` FOREIGN KEY (`hospitalid`) REFERENCES `hospital` (`hospitalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital` (
  `hospitalid` int(11) NOT NULL,
  `hospitalname` varchar(255) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`hospitalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sysadmin
-- ----------------------------
DROP TABLE IF EXISTS `sysadmin`;
CREATE TABLE `sysadmin` (
  `adminid` int(11) NOT NULL,
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
  `id` int(11) NOT NULL,
  `password` varchar(1024) DEFAULT NULL,
  `regtime` datetime DEFAULT NULL,
  `lastlogin` datetime DEFAULT NULL,
  `idno` varchar(18) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `creditlevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
