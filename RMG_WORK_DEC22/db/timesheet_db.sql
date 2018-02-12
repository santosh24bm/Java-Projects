/*
SQLyog Community v11.52 (32 bit)
MySQL - 5.6.23-log : Database - timesheet_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`timesheet_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `timesheet_db`;

/*Table structure for table `charge_code_master` */

DROP TABLE IF EXISTS `charge_code_master`;

CREATE TABLE `charge_code_master` (
  `charge_code_id` int(11) NOT NULL,
  `charge_code` varchar(45) DEFAULT NULL,
  `charge_code_description` varchar(200) DEFAULT NULL,
  `charge_code_owner` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`charge_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `charge_code_master` */

insert  into `charge_code_master`(`charge_code_id`,`charge_code`,`charge_code_description`,`charge_code_owner`) values (1,'VCN0001','Vacation','HR Head'),(2,'Sales0002','ESG Support','ESG Head'),(3,'Pract0003','Innovation','Rajesh Sarangapani'),(4,'Pract0005','PerfModule','Rajesh Sarangapani'),(5,'Pract0006','VeritaImplementations','Moshe Dayan'),(6,'Pract0008','VerityPlatfrom','E001286');

/*Table structure for table `menu_master` */

DROP TABLE IF EXISTS `menu_master`;

CREATE TABLE `menu_master` (
  `menu_id` bigint(20) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `menu_master` */

insert  into `menu_master`(`menu_id`,`menu_name`) values (1,'Record'),(2,'Review'),(3,'Reports'),(4,'Autherise'),(5,'Create'),(6,'ChargeCode');

/*Table structure for table `role_master` */

DROP TABLE IF EXISTS `role_master`;

CREATE TABLE `role_master` (
  `role_id` bigint(20) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_master` */

insert  into `role_master`(`role_id`,`role_name`) values (1,'Employee'),(2,'Reviewer'),(3,'PMO'),(4,'Account Manager'),(5,'DU Lead'),(6,'BU Lead');

/*Table structure for table `role_menu_map` */

DROP TABLE IF EXISTS `role_menu_map`;

CREATE TABLE `role_menu_map` (
  `role_menu_map_id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`role_menu_map_id`),
  KEY `menuIdFk_idx` (`menu_id`),
  KEY `roleIdFk_idx` (`role_id`),
  CONSTRAINT `menuIdFk` FOREIGN KEY (`menu_id`) REFERENCES `menu_master` (`menu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `roleIdMenuIdFk` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_menu_map` */

insert  into `role_menu_map`(`role_menu_map_id`,`role_id`,`menu_id`) values (1,1,1),(2,1,2),(3,1,3),(4,2,4),(5,2,5);

/*Table structure for table `time_period_master` */

DROP TABLE IF EXISTS `time_period_master`;

CREATE TABLE `time_period_master` (
  `time_period_id` int(11) NOT NULL,
  `time_period_name` varchar(45) DEFAULT NULL,
  `time_period_lastdate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`time_period_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `time_period_master` */

insert  into `time_period_master`(`time_period_id`,`time_period_name`,`time_period_lastdate`) values (1,'1Oct2017 To 15Oct2017','15Oct2017'),(2,'16Oct2017 To 31Oct2017','31Oct2017'),(3,'1Nov2017 To 15Nov2017','15Nov2017'),(4,'16Nov2017 To 31Nov2017','31Nov2017'),(5,'1Dec2017 To 15Dec2017','15Dec2017'),(6,'16Dec2017 To 31Dec2017','31Dec2017');

/*Table structure for table `timesheet_details` */

DROP TABLE IF EXISTS `timesheet_details`;

CREATE TABLE `timesheet_details` (
  `idTimeSheet_Details` int(11) NOT NULL,
  `emp_id` varchar(45) DEFAULT NULL,
  `charge_code_id` int(11) DEFAULT NULL,
  `time_period_id` int(11) DEFAULT NULL,
  `day_one` int(11) DEFAULT '0',
  `day_two` int(11) DEFAULT NULL,
  `day_three` int(11) DEFAULT NULL,
  `day_four` int(11) DEFAULT NULL,
  `day_five` int(11) DEFAULT NULL,
  `day_six` int(11) DEFAULT NULL,
  `day_seven` int(11) DEFAULT NULL,
  `day_eight` int(11) DEFAULT NULL,
  `day_nine` int(11) DEFAULT NULL,
  `day_ten` int(11) DEFAULT NULL,
  `day_eleven` int(11) DEFAULT NULL,
  `day_twelve` int(11) DEFAULT NULL,
  `day_thirteen` int(11) DEFAULT NULL,
  `day_fourteen` int(11) DEFAULT NULL,
  `day_fifteen` int(11) DEFAULT NULL,
  `day_sixteen` int(11) DEFAULT NULL,
  KEY `tpFk1_idx` (`time_period_id`),
  KEY `timesheetChCdFk_idx` (`charge_code_id`),
  KEY `timesheetEmpFk_idx` (`emp_id`),
  CONSTRAINT `timesheetEmpFk` FOREIGN KEY (`emp_id`) REFERENCES `user_master` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `timesheetTPFK` FOREIGN KEY (`time_period_id`) REFERENCES `time_period_master` (`time_period_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tmesheetChgFk` FOREIGN KEY (`charge_code_id`) REFERENCES `charge_code_master` (`charge_code_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `timesheet_details` */

insert  into `timesheet_details`(`idTimeSheet_Details`,`emp_id`,`charge_code_id`,`time_period_id`,`day_one`,`day_two`,`day_three`,`day_four`,`day_five`,`day_six`,`day_seven`,`day_eight`,`day_nine`,`day_ten`,`day_eleven`,`day_twelve`,`day_thirteen`,`day_fourteen`,`day_fifteen`,`day_sixteen`) values (101,'E001286',4,2,4,5,6,8,9,1,3,6,7,8,9,8,5,3,4,6);

/*Table structure for table `user_charge_code_map` */

DROP TABLE IF EXISTS `user_charge_code_map`;

CREATE TABLE `user_charge_code_map` (
  `emp_charg_code_map_id` int(11) NOT NULL,
  `emp_id` varchar(45) DEFAULT NULL,
  `charge_code_id` varchar(45) DEFAULT NULL,
  `charge_code_assign_by` varchar(45) DEFAULT NULL,
  `user_charge_code_assign_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`emp_charg_code_map_id`),
  KEY `usrChargeCodeFk_idx` (`charge_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_charge_code_map` */

insert  into `user_charge_code_map`(`emp_charg_code_map_id`,`emp_id`,`charge_code_id`,`charge_code_assign_by`,`user_charge_code_assign_date`) values (1,'E001286','1','E001285','13/10/2017'),(2,'E001286','2','E001285','13/10/2017');

/*Table structure for table `user_master` */

DROP TABLE IF EXISTS `user_master`;

CREATE TABLE `user_master` (
  `user_id` varchar(10) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_master` */

insert  into `user_master`(`user_id`,`user_name`,`user_email`) values ('E001285','Madhu Sudhan Chiluka','E001285@cigniti.com'),('E001286','Kasi Viswanath Kurva','E001286@cigniti.com');

/*Table structure for table `user_role_map` */

DROP TABLE IF EXISTS `user_role_map`;

CREATE TABLE `user_role_map` (
  `user_role_map_id` bigint(20) NOT NULL,
  `user_id` varchar(10) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_role_map_id`),
  KEY `userFK_idx` (`user_id`),
  KEY `roleFk_idx` (`role_id`),
  CONSTRAINT `roleFk` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usrRoleFk` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role_map` */

insert  into `user_role_map`(`user_role_map_id`,`user_id`,`role_id`) values (1,'E001286',2);

/*Table structure for table `login_details_master` */

DROP TABLE IF EXISTS `login_details_master`;

/*!50001 DROP VIEW IF EXISTS `login_details_master` */;
/*!50001 DROP TABLE IF EXISTS `login_details_master` */;

/*!50001 CREATE TABLE  `login_details_master`(
 `employee_id` varchar(10) ,
 `employee_name` varchar(255) ,
 `emp_role_id` bigint(20) ,
 `emp_role_name` varchar(255) 
)*/;

/*View structure for view login_details_master */

/*!50001 DROP TABLE IF EXISTS `login_details_master` */;
/*!50001 DROP VIEW IF EXISTS `login_details_master` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `login_details_master` AS select `um`.`user_id` AS `employee_id`,`um`.`user_name` AS `employee_name`,`rm`.`role_id` AS `emp_role_id`,`rm`.`role_name` AS `emp_role_name` from ((`user_master` `um` join `role_master` `rm`) join `user_role_map` `urm`) where ((`urm`.`user_id` = `um`.`user_id`) and (`urm`.`role_id` = `rm`.`role_id`)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
