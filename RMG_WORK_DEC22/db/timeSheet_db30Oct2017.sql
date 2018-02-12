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
  `charge_code_type` varchar(100) DEFAULT NULL,
  `charge_code_subtype` varchar(100) DEFAULT NULL,
  `client` varchar(200) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`charge_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `charge_code_master` */

insert  into `charge_code_master`(`charge_code_id`,`charge_code`,`charge_code_description`,`charge_code_owner`,`charge_code_type`,`charge_code_subtype`,`client`,`country`) values (1,'VCN0001','Vacation','HR Head','HROperations','Test','Cigniti','India'),(2,'Sales0002','ESG Support','ESG Head','ESGSupport','Test','Amzen','India'),(3,'Pract0003','Innovation','Rajesh Sarangapani','Business Development','Test','ATD','India'),(4,'Pract0005','PerfModule','Rajesh Sarangapani','Chargable','Test','Lennox','India'),(5,'Pract0006','VeritaImplementations','Moshe Dayan','Chargable','Test','Maxlife','India'),(7,'Pract0011','Performance','Rajesh Sarangapani','Chargable','Client Billing','ATD','USA'),(8,'Pract0010','Performance','Rajesh Sarangapani','Chargable','Client Billing','ATD','USA');

/*Table structure for table `emp_reportto` */

DROP TABLE IF EXISTS `emp_reportto`;

CREATE TABLE `emp_reportto` (
  `emp_number` varchar(45) NOT NULL DEFAULT '0',
  `sup_emp_number` varchar(45) NOT NULL DEFAULT '0',
  `reporting_mode` varchar(45) NOT NULL DEFAULT '0',
  `appr_emp_number` varchar(45) DEFAULT NULL,
  `copyto_emp_number` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `emp_reportto` */

insert  into `emp_reportto`(`emp_number`,`sup_emp_number`,`reporting_mode`,`appr_emp_number`,`copyto_emp_number`) values ('E001286','E001070','Direct','E001070','E001070'),('E001322','E001070','Direct','E001070','E001070'),('E001113','E001286','Direct','E001286','E001286'),('E001272','E001286','Direct','E001286','E001286'),('E001102','E001286','Indirect','E001286','E001286'),('E001071','E001001','Direct','E001072,E001073','E001074,E001076');

/*Table structure for table `holidaycalendar` */

DROP TABLE IF EXISTS `holidaycalendar`;

CREATE TABLE `holidaycalendar` (
  `cal_id` int(11) NOT NULL,
  `cal_date` varchar(45) DEFAULT NULL,
  `cal_day` varchar(45) DEFAULT NULL,
  `holiday_description` varchar(400) DEFAULT NULL,
  `holiday_type` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `isWeekend` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `holidaycalendar` */

insert  into `holidaycalendar`(`cal_id`,`cal_date`,`cal_day`,`holiday_description`,`holiday_type`,`country`,`location`,`isWeekend`) values (1,'26-Jan-2017','Thursday','Republic Day','National','India','Hyd','no'),(2,'29-Mar-17','Wednesday','Ugadi','Regional','India','Hyd','no'),(3,'2-Jun-17','Friday','Telangana Formation Day','Regional','India','Hyd','no'),(4,'26-Jun-17','Monday','Ramzan (Eidul Fitar)','Regional','India','Hyd','no'),(5,'15-Aug-17','Tuesday','Independence Day','National','India','Hyd','no'),(6,'25-Aug-17','Friday','Ganesh Chaturthi','Regional','India','Hyd','no'),(7,'28-Sep-17','Thursday','Durgastami','Regional','India','Hyd','no'),(8,'2-Oct-17','Monday','Mahatma Gandhi Jayanthi','National','India','Hyd','no'),(9,'19-Oct-17','Thursday','Deepavali','Regional','India','Hyd','no'),(10,'25-Dec-17','Monday','Christmas','Regional','India','Hyd','no');

/*Table structure for table `logindetailsbean` */

DROP TABLE IF EXISTS `logindetailsbean`;

CREATE TABLE `logindetailsbean` (
  `employeeId` varchar(255) NOT NULL,
  `emp_role_id` decimal(19,2) DEFAULT NULL,
  `emp_role_name` varchar(255) DEFAULT NULL,
  `employeeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `logindetailsbean` */

/*Table structure for table `menu_master` */

DROP TABLE IF EXISTS `menu_master`;

CREATE TABLE `menu_master` (
  `menu_id` bigint(20) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `menu_master` */

insert  into `menu_master`(`menu_id`,`menu_name`) values (1,'Record'),(2,'Review'),(3,'Reports'),(4,'Autherise'),(5,'Create'),(6,'ChargeCode'),(7,'Assignments');

/*Table structure for table `period_details_vw` */

DROP TABLE IF EXISTS `period_details_vw`;

CREATE TABLE `period_details_vw` (
  `time_period_id` int(11) NOT NULL,
  `time_period_lastdate` datetime DEFAULT NULL,
  `time_period_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`time_period_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `period_details_vw` */

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

insert  into `role_menu_map`(`role_menu_map_id`,`role_id`,`menu_id`) values (1,1,1),(4,2,1),(5,2,2),(6,2,7);

/*Table structure for table `time_period_master` */

DROP TABLE IF EXISTS `time_period_master`;

CREATE TABLE `time_period_master` (
  `time_period_id` int(11) NOT NULL,
  `time_period_name` varchar(45) DEFAULT NULL,
  `time_period_lastdate` date NOT NULL,
  PRIMARY KEY (`time_period_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `time_period_master` */

insert  into `time_period_master`(`time_period_id`,`time_period_name`,`time_period_lastdate`) values (1,'1 Jan 2017 - 15 Jan 2017','2017-01-15'),(2,'16 Jan 2017 - 31 Jan 2017','2017-01-31'),(3,'1 Feb 2017 To 15 Feb 2017','2017-02-15'),(4,'16 Feb 2017 - 28 Feb 2017','2017-02-28'),(5,'1 Mar 2017 - 15 Mar 2017','2017-03-15'),(6,'16 Mar 2017 - 31 Mar 2017','2017-03-31'),(7,'1 Apr 2017 - 15 Apr 2017','2017-04-15'),(8,'16 Apr 2017 - 30 Apr 2017','2017-04-30'),(9,'1 May 2017 - 15 May 2017','2017-05-15'),(10,'16 May 2017 - 31 May 2017','2017-05-31'),(11,'1 Jun 2017 - 15 Jun 2017','2017-06-15'),(12,'16 Jun 2017 - 30 Jun 2017','2017-06-30'),(13,'1 Jul 2017 - 15 Jul 2017','2017-07-15'),(14,'16 Jul 2017 - 31 Jul 2017','2017-07-31'),(15,'1 Aug 2017 - 15 Aug 2017','2017-08-15'),(16,'16 Aug 2017 - 31 Aug 2017','2017-08-31'),(17,'1 Sep 2017 - 15 Sep 2017','2017-09-15'),(18,'16 Sep 2017 - 30 Sep 2017','2017-09-30'),(19,'1 Oct 2017 - 15 Oct 2017','2017-10-15'),(20,'16 Oct 2017 - 31 Oct 2017','2017-10-31'),(21,'1 Nov 2017 - 15 Nov 2017','2017-11-15'),(22,'16 Nov 2017 - 30 Nov 2017','2017-11-30'),(23,'1 Dec 2017 - 15 Dec 2017','2017-12-15'),(24,'16 Dec 2017 - 31 Dec 2017','2017-12-31'),(25,'1 Jan 2018 - 15 Jan 2018','2018-01-15'),(26,'16 Jan 2018 - 31 Jan 2018','2018-01-31'),(27,'1 Feb 2018 - 15 Feb 2018','2018-02-15'),(28,'16 Feb 2018 - 28 Feb 2018','2018-02-28'),(29,'1 Mar 2018 - 15 Mar 2018','2018-03-15'),(30,'16 Mar 2018 - 31 Mar 2018','2018-03-31'),(31,'1 Apr 2018 - 15 Apr 2018','2018-04-15'),(32,'16 Apr 2018 - 30 Apr 2018','2018-04-30'),(33,'1 May 2018 - 15 May 2018','2018-05-15'),(34,'16 May 2018 - 31 May 2018','2018-05-31'),(35,'1 Jun 2018 - 15 Jun 2018','2018-06-15'),(36,'16 Jun 2018 - 30 Jun 2018','2018-06-30');

/*Table structure for table `timesheet_details` */

DROP TABLE IF EXISTS `timesheet_details`;

CREATE TABLE `timesheet_details` (
  `idTimeSheet_Details` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` varchar(45) DEFAULT NULL,
  `charge_code_id` int(11) DEFAULT NULL,
  `time_period_id` int(11) DEFAULT NULL,
  `day_one` float DEFAULT '0',
  `day_two` float DEFAULT '0',
  `day_three` float DEFAULT '0',
  `day_four` float DEFAULT '0',
  `day_five` float DEFAULT '0',
  `day_six` float DEFAULT '0',
  `day_seven` float DEFAULT '0',
  `day_eight` float DEFAULT '0',
  `day_nine` float DEFAULT '0',
  `day_ten` float DEFAULT '0',
  `day_eleven` float DEFAULT '0',
  `day_twelve` float DEFAULT '0',
  `day_thirteen` float DEFAULT '0',
  `day_fourteen` float DEFAULT '0',
  `day_fifteen` float DEFAULT '0',
  `day_sixteen` float DEFAULT '0',
  `status` varchar(20) NOT NULL DEFAULT 'New',
  `comments` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idTimeSheet_Details`),
  KEY `tpFk1_idx` (`time_period_id`),
  KEY `timesheetChCdFk_idx` (`charge_code_id`),
  KEY `timesheetEmpFk_idx` (`emp_id`),
  CONSTRAINT `timesheetEmpFk` FOREIGN KEY (`emp_id`) REFERENCES `user_master` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `timesheetTPFK` FOREIGN KEY (`time_period_id`) REFERENCES `time_period_master` (`time_period_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tmesheetChgFk` FOREIGN KEY (`charge_code_id`) REFERENCES `charge_code_master` (`charge_code_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

/*Data for the table `timesheet_details` */

insert  into `timesheet_details`(`idTimeSheet_Details`,`emp_id`,`charge_code_id`,`time_period_id`,`day_one`,`day_two`,`day_three`,`day_four`,`day_five`,`day_six`,`day_seven`,`day_eight`,`day_nine`,`day_ten`,`day_eleven`,`day_twelve`,`day_thirteen`,`day_fourteen`,`day_fifteen`,`day_sixteen`,`status`,`comments`) values (125,'E001272',3,20,4,5,6,8,9,1,3,6,7,8,9,8,5,3,4,6,'Approved',NULL),(134,'E001113',3,20,8,9,8,9,9,9,9,9,8,8,8,9,9,9,0,9,'Approved',NULL),(135,'E001286',1,20,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,'Submitted',NULL),(136,'E001286',2,20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Submitted',NULL),(137,'E001286',3,20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Submitted',NULL),(138,'E003623',1,21,4.5,5.6,6.8,8.5,9,1,3,6,7,8,9,8,5,3,4,6,'Rejected','This timesheet is not filled properly');

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

insert  into `user_charge_code_map`(`emp_charg_code_map_id`,`emp_id`,`charge_code_id`,`charge_code_assign_by`,`user_charge_code_assign_date`) values (1,'E001286','1','E001285','13/10/2017'),(2,'E001286','2','E001285','13/10/2017'),(3,'E001286','3','E001285','19/10/2017'),(4,'E001113','3','E001286','12/06/2016'),(5,'E001272','3','E001286','19/04/2016'),(6,'E003623','3','E001286','15/12/2016'),(7,'E001102','3','E003623','20/01/2017');

/*Table structure for table `user_master` */

DROP TABLE IF EXISTS `user_master`;

CREATE TABLE `user_master` (
  `user_id` varchar(10) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_master` */

insert  into `user_master`(`user_id`,`user_name`,`user_email`) values ('E001102','Sankar Kurakula','E001102@cigniti.com'),('E001113','Bharat Kumar Nakka','E001113@cigniti.com'),('E001272','Suneel Kumar Rallapalli','E001272@cigniti.com'),('E001285','Madhu Sudhan Chiluka','E001285@cigniti.com'),('E001286','Kasi Viswanath Kurva','E001286@cigniti.com'),('E003623','Kumar Gaurav','E003623@cigniti.com');

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

insert  into `user_role_map`(`user_role_map_id`,`user_id`,`role_id`) values (1,'E001286',2),(2,'E003623',1),(3,'E001113',1),(4,'E001272',1),(5,'E001102',2);

/*Table structure for table `current_period_details_vw` */

DROP TABLE IF EXISTS `current_period_details_vw`;

/*!50001 DROP VIEW IF EXISTS `current_period_details_vw` */;
/*!50001 DROP TABLE IF EXISTS `current_period_details_vw` */;

/*!50001 CREATE TABLE  `current_period_details_vw`(
 `time_period_id` int(11) ,
 `time_period_name` varchar(45) ,
 `time_period_lastdate` date 
)*/;

/*Table structure for table `emp_timesheet_details_vw` */

DROP TABLE IF EXISTS `emp_timesheet_details_vw`;

/*!50001 DROP VIEW IF EXISTS `emp_timesheet_details_vw` */;
/*!50001 DROP TABLE IF EXISTS `emp_timesheet_details_vw` */;

/*!50001 CREATE TABLE  `emp_timesheet_details_vw`(
 `employee_id` varchar(45) ,
 `employee_name` varchar(255) ,
 `assignment_id` int(11) ,
 `project_code` varchar(45) ,
 `project_desc` varchar(200) ,
 `period_id` int(11) ,
 `period_name` varchar(45) ,
 `tstatus` varchar(20) ,
 `comments` varchar(200) ,
 `day_one` float ,
 `day_two` float ,
 `day_three` float ,
 `day_four` float ,
 `day_five` float ,
 `day_six` float ,
 `day_seven` float ,
 `day_eight` float ,
 `day_nine` float ,
 `day_ten` float ,
 `day_eleven` float ,
 `day_twelve` float ,
 `day_thirteen` float ,
 `day_fourteen` float ,
 `day_fifteen` float ,
 `day_sixteen` float 
)*/;

/*Table structure for table `get_last_date_vw` */

DROP TABLE IF EXISTS `get_last_date_vw`;

/*!50001 DROP VIEW IF EXISTS `get_last_date_vw` */;
/*!50001 DROP TABLE IF EXISTS `get_last_date_vw` */;

/*!50001 CREATE TABLE  `get_last_date_vw`(
 `currDate` date ,
 `last_date` varchar(10) 
)*/;

/*Table structure for table `login_details_master_vw` */

DROP TABLE IF EXISTS `login_details_master_vw`;

/*!50001 DROP VIEW IF EXISTS `login_details_master_vw` */;
/*!50001 DROP TABLE IF EXISTS `login_details_master_vw` */;

/*!50001 CREATE TABLE  `login_details_master_vw`(
 `employee_id` varchar(10) ,
 `employee_name` varchar(255) ,
 `emp_role_id` bigint(20) ,
 `emp_role_name` varchar(255) 
)*/;

/*Table structure for table `resource_list_vw` */

DROP TABLE IF EXISTS `resource_list_vw`;

/*!50001 DROP VIEW IF EXISTS `resource_list_vw` */;
/*!50001 DROP TABLE IF EXISTS `resource_list_vw` */;

/*!50001 CREATE TABLE  `resource_list_vw`(
 `employee_id` varchar(45) ,
 `supervisor_emp_id` varchar(45) ,
 `approver_emp_id` varchar(45) ,
 `copyto_emp_number` varchar(45) ,
 `time_period_id` int(11) ,
 `status` varchar(20) 
)*/;

/*Table structure for table `reviewer_list_vw` */

DROP TABLE IF EXISTS `reviewer_list_vw`;

/*!50001 DROP VIEW IF EXISTS `reviewer_list_vw` */;
/*!50001 DROP TABLE IF EXISTS `reviewer_list_vw` */;

/*!50001 CREATE TABLE  `reviewer_list_vw`(
 `emp_number` varchar(45) ,
 `Supervisor` varchar(10) 
)*/;

/*Table structure for table `user_charge_code_map_vw` */

DROP TABLE IF EXISTS `user_charge_code_map_vw`;

/*!50001 DROP VIEW IF EXISTS `user_charge_code_map_vw` */;
/*!50001 DROP TABLE IF EXISTS `user_charge_code_map_vw` */;

/*!50001 CREATE TABLE  `user_charge_code_map_vw`(
 `charge_code` varchar(45) ,
 `charge_code_description` varchar(200) ,
 `emp_charg_code_map_id` int(11) ,
 `emp_id` varchar(45) ,
 `charge_code_id` varchar(45) ,
 `charge_code_assign_by` varchar(45) ,
 `user_charge_code_assign_date` varchar(45) ,
 `charge_code_owner` varchar(45) 
)*/;

/*View structure for view current_period_details_vw */

/*!50001 DROP TABLE IF EXISTS `current_period_details_vw` */;
/*!50001 DROP VIEW IF EXISTS `current_period_details_vw` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `current_period_details_vw` AS select `tpm`.`time_period_id` AS `time_period_id`,`tpm`.`time_period_name` AS `time_period_name`,`tpm`.`time_period_lastdate` AS `time_period_lastdate` from (`get_last_date_vw` `ldv` join `time_period_master` `tpm`) where (`ldv`.`last_date` = `tpm`.`time_period_lastdate`) */;

/*View structure for view emp_timesheet_details_vw */

/*!50001 DROP TABLE IF EXISTS `emp_timesheet_details_vw` */;
/*!50001 DROP VIEW IF EXISTS `emp_timesheet_details_vw` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `emp_timesheet_details_vw` AS select `td`.`emp_id` AS `employee_id`,`um`.`user_name` AS `employee_name`,`td`.`charge_code_id` AS `assignment_id`,`ccm`.`charge_code` AS `project_code`,`ccm`.`charge_code_description` AS `project_desc`,`tpm`.`time_period_id` AS `period_id`,`tpm`.`time_period_name` AS `period_name`,`td`.`status` AS `tstatus`,`td`.`comments` AS `comments`,`td`.`day_one` AS `day_one`,`td`.`day_two` AS `day_two`,`td`.`day_three` AS `day_three`,`td`.`day_four` AS `day_four`,`td`.`day_five` AS `day_five`,`td`.`day_six` AS `day_six`,`td`.`day_seven` AS `day_seven`,`td`.`day_eight` AS `day_eight`,`td`.`day_nine` AS `day_nine`,`td`.`day_ten` AS `day_ten`,`td`.`day_eleven` AS `day_eleven`,`td`.`day_twelve` AS `day_twelve`,`td`.`day_thirteen` AS `day_thirteen`,`td`.`day_fourteen` AS `day_fourteen`,`td`.`day_fifteen` AS `day_fifteen`,`td`.`day_sixteen` AS `day_sixteen` from (((`timesheet_details` `td` join `charge_code_master` `ccm`) join `time_period_master` `tpm`) join `user_master` `um`) where ((`td`.`charge_code_id` = `ccm`.`charge_code_id`) and (`td`.`emp_id` = `um`.`user_id`) and (`td`.`time_period_id` = `tpm`.`time_period_id`)) */;

/*View structure for view get_last_date_vw */

/*!50001 DROP TABLE IF EXISTS `get_last_date_vw` */;
/*!50001 DROP VIEW IF EXISTS `get_last_date_vw` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `get_last_date_vw` AS select curdate() AS `currDate`,(case when (curdate() < date_format(now(),'%Y-%m-15')) then date_format(now(),'%Y-%m-15') else last_day(curdate()) end) AS `last_date` */;

/*View structure for view login_details_master_vw */

/*!50001 DROP TABLE IF EXISTS `login_details_master_vw` */;
/*!50001 DROP VIEW IF EXISTS `login_details_master_vw` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `login_details_master_vw` AS select `um`.`user_id` AS `employee_id`,`um`.`user_name` AS `employee_name`,`rm`.`role_id` AS `emp_role_id`,`rm`.`role_name` AS `emp_role_name` from ((`user_master` `um` join `role_master` `rm`) join `user_role_map` `urm`) where ((`urm`.`user_id` = `um`.`user_id`) and (`urm`.`role_id` = `rm`.`role_id`)) */;

/*View structure for view resource_list_vw */

/*!50001 DROP TABLE IF EXISTS `resource_list_vw` */;
/*!50001 DROP VIEW IF EXISTS `resource_list_vw` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `resource_list_vw` AS select distinct `er`.`emp_number` AS `employee_id`,`er`.`sup_emp_number` AS `supervisor_emp_id`,`er`.`appr_emp_number` AS `approver_emp_id`,`er`.`copyto_emp_number` AS `copyto_emp_number`,`td`.`time_period_id` AS `time_period_id`,`td`.`status` AS `status` from ((`user_master` `um` join `emp_reportto` `er`) join `timesheet_details` `td`) where ((`er`.`sup_emp_number` = `um`.`user_id`) and (`er`.`emp_number` = `td`.`emp_id`)) */;

/*View structure for view reviewer_list_vw */

/*!50001 DROP TABLE IF EXISTS `reviewer_list_vw` */;
/*!50001 DROP VIEW IF EXISTS `reviewer_list_vw` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `reviewer_list_vw` AS select `emp_reportto`.`emp_number` AS `emp_number`,'Supervisor' AS `Supervisor` from `emp_reportto` where (`emp_reportto`.`sup_emp_number` = 'E001286') union select `emp_reportto`.`emp_number` AS `emp_number`,'Approve' AS `Approve` from `emp_reportto` where (`emp_reportto`.`appr_emp_number` = 'E001286') union select `emp_reportto`.`emp_number` AS `emp_number`,'CopyTo' AS `CopyTo` from `emp_reportto` where (`emp_reportto`.`copyto_emp_number` = 'E001286') */;

/*View structure for view user_charge_code_map_vw */

/*!50001 DROP TABLE IF EXISTS `user_charge_code_map_vw` */;
/*!50001 DROP VIEW IF EXISTS `user_charge_code_map_vw` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `user_charge_code_map_vw` AS select `ccm`.`charge_code` AS `charge_code`,`ccm`.`charge_code_description` AS `charge_code_description`,`ucm`.`emp_charg_code_map_id` AS `emp_charg_code_map_id`,`ucm`.`emp_id` AS `emp_id`,`ucm`.`charge_code_id` AS `charge_code_id`,`ucm`.`charge_code_assign_by` AS `charge_code_assign_by`,`ucm`.`user_charge_code_assign_date` AS `user_charge_code_assign_date`,`ccm`.`charge_code_owner` AS `charge_code_owner` from (`user_charge_code_map` `ucm` join `charge_code_master` `ccm`) where (`ucm`.`charge_code_id` = `ccm`.`charge_code_id`) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
