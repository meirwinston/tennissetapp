DROP DATABASE `tennissetapp`;
CREATE DATABASE `tennissetapp` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `tennissetapp`;

DROP TABLE IF EXISTS `UserConnection`;

CREATE TABLE `UserConnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL DEFAULT '',
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(255) NOT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB ;

/*Table structure for table `addresses` */

DROP TABLE IF EXISTS `addresses`;

CREATE TABLE `addresses` (
  `latitude` double NOT NULL DEFAULT '0',
  `longitude` double NOT NULL DEFAULT '0',
  `streetNumber` varchar(50) DEFAULT NULL,
  `route` varchar(100) DEFAULT NULL,
  `routeShortName` varchar(100) DEFAULT NULL,
  `locality` varchar(100) DEFAULT NULL,
  `localityShortName` varchar(100) DEFAULT NULL,
  `sublocality` varchar(100) DEFAULT NULL,
  `sublocalityShortName` varchar(100) DEFAULT NULL,
  `administrativeAreaLevel2` varchar(100) DEFAULT NULL,
  `administrativeAreaLevel2ShortName` varchar(100) DEFAULT NULL,
  `administrativeAreaLevel1` varchar(100) DEFAULT NULL,
  `administrativeAreaLevel1ShortName` varchar(100) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `countryShortName` varchar(3) DEFAULT NULL,
  `postalCode` varchar(10) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`latitude`,`longitude`)
) ENGINE=InnoDB ;

/*Table structure for table `image_files` */

DROP TABLE IF EXISTS `image_files`;

CREATE TABLE `image_files` (
  `imageFileId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `width` int(11) DEFAULT NULL COMMENT 'in pixels',
  `height` int(11) DEFAULT NULL COMMENT 'in pixels',
  `size` int(11) DEFAULT NULL COMMENT 'in bytes',
  `format` varchar(20) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `dirPath` varchar(255) DEFAULT NULL COMMENT 'USER_DEFAULT, GARBAGE, folder management, user will be able to create folders',
  `album` varchar(50) DEFAULT 'Default',
  PRIMARY KEY (`imageFileId`),
  KEY `FK_ImageFiles_UserAccounts` (`ownerId`),
  CONSTRAINT `FK_ImageFiles_UserAccounts` FOREIGN KEY (`ownerId`) REFERENCES `user_accounts` (`userAccountId`)
) ENGINE=InnoDB AUTO_INCREMENT=88 ;

/*Table structure for table `match_members` */

DROP TABLE IF EXISTS `match_members`;

CREATE TABLE `match_members` (
  `userAccountId` bigint(20) NOT NULL,
  `matchId` bigint(20) NOT NULL,
  `joinedOn` datetime NOT NULL,
  PRIMARY KEY (`userAccountId`,`matchId`),
  KEY `FK_match_members_matches` (`matchId`),
  CONSTRAINT `FK_match_members_matches` FOREIGN KEY (`matchId`) REFERENCES `matches` (`matchId`),
  CONSTRAINT `FK_match_members_useraccounts` FOREIGN KEY (`userAccountId`) REFERENCES `user_accounts` (`userAccountId`)
) ENGINE=InnoDB ;

/*Table structure for table `matches` */

DROP TABLE IF EXISTS `matches`;

CREATE TABLE `matches` (
  `matchId` bigint(20) NOT NULL AUTO_INCREMENT,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `levelOfPlayMin` float NOT NULL,
  `levelOfPlayMax` float NOT NULL,
  `visibility` varchar(20) NOT NULL,
  `playSingles` tinyint(1) DEFAULT '0',
  `playDoubles` tinyint(1) DEFAULT '0',
  `playFullMatch` tinyint(1) DEFAULT '0',
  `playPoints` tinyint(1) DEFAULT '0',
  `playHittingAround` tinyint(1) DEFAULT '0',
  `tennisCenterId` bigint(20) NOT NULL,
  `orgenizerId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`matchId`),
  KEY `FK_Matches_TennisCenters` (`tennisCenterId`),
  KEY `FK_Matches_UserAccounts` (`orgenizerId`),
  CONSTRAINT `FK_Matches_TennisCenters` FOREIGN KEY (`tennisCenterId`) REFERENCES `tennis_centers` (`tennisCenterId`),
  CONSTRAINT `FK_Matches_UserAccounts` FOREIGN KEY (`orgenizerId`) REFERENCES `user_accounts` (`userAccountId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;

/*Table structure for table `mates` */

DROP TABLE IF EXISTS `mates`;

CREATE TABLE `mates` (
  `userAccountId` bigint(20) NOT NULL,
  `mateAccountId` bigint(20) NOT NULL,
  `createdOn` datetime NOT NULL,
  PRIMARY KEY (`userAccountId`,`mateAccountId`),
  KEY `FK_mates_mateaccounts` (`mateAccountId`),
  CONSTRAINT `FK_mates_mateaccounts` FOREIGN KEY (`mateAccountId`) REFERENCES `user_accounts` (`userAccountId`),
  CONSTRAINT `FK_mates_useraccounts` FOREIGN KEY (`userAccountId`) REFERENCES `user_accounts` (`userAccountId`)
) ENGINE=InnoDB ;

/*Table structure for table `player_profiles` */

DROP TABLE IF EXISTS `player_profiles`;

CREATE TABLE `player_profiles` (
  `userAccountId` bigint(20) NOT NULL,
  `createdOn` datetime DEFAULT NULL,
  `createdByIP` varchar(20) DEFAULT NULL,
  `modifiedByIP` varchar(20) DEFAULT NULL,
  `profileImageFileId` bigint(20) DEFAULT NULL,
  `aboutMe` varchar(1000) DEFAULT NULL,
  `levelOfPlay` float DEFAULT NULL,
  `hand` varchar(8) DEFAULT NULL,
  `playSingles` tinyint(1) DEFAULT '0',
  `playDoubles` tinyint(1) DEFAULT '0',
  `playFullMatch` tinyint(1) DEFAULT '0',
  `playPoints` tinyint(1) DEFAULT '0',
  `playHittingAround` tinyint(1) DEFAULT '0',
  `availableWeekendMorning` tinyint(1) DEFAULT '0',
  `availableWeekendAfternoon` tinyint(1) DEFAULT '0',
  `availableWeekendEvening` tinyint(1) DEFAULT '0',
  `availableWeekdayMorning` tinyint(1) DEFAULT '0',
  `availableWeekdayAfternoon` tinyint(1) DEFAULT '0',
  `availableWeekdayEvening` tinyint(1) DEFAULT '0',
  `attendance` float DEFAULT '0',
  `punctuality` float DEFAULT '0',
  `tennisLevel` float DEFAULT '0',
  PRIMARY KEY (`userAccountId`),
  KEY `FK_player_profiles_image_files` (`profileImageFileId`),
  CONSTRAINT `FK_player_profiles_image_files` FOREIGN KEY (`profileImageFileId`) REFERENCES `image_files` (`imageFileId`),
  CONSTRAINT `FK_player_profiles_user_accounts` FOREIGN KEY (`userAccountId`) REFERENCES `user_accounts` (`userAccountId`)
) ENGINE=InnoDB ;

/*Table structure for table `playerprofile_tenniscenter` */

DROP TABLE IF EXISTS `playerprofile_tenniscenter`;

CREATE TABLE `playerprofile_tenniscenter` (
  `userAccountId` bigint(20) NOT NULL,
  `tennisCenterId` bigint(20) NOT NULL,
  PRIMARY KEY (`userAccountId`,`tennisCenterId`),
  KEY `FK_UserAccount_TennisCourt_TennisCourts` (`tennisCenterId`),
  CONSTRAINT `FK_playerprofile_tenniscenter_player_profile` FOREIGN KEY (`userAccountId`) REFERENCES `player_profiles` (`userAccountId`),
  CONSTRAINT `FK_playerprofile_tenniscenter_tennis_centers` FOREIGN KEY (`tennisCenterId`) REFERENCES `tennis_centers` (`tennisCenterId`)
) ENGINE=InnoDB ;

/*Table structure for table `schema_version` */

DROP TABLE IF EXISTS `schema_version`;

CREATE TABLE `schema_version` (
  `version_rank` int(11) NOT NULL,
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`version`),
  KEY `schema_version_vr_idx` (`version_rank`),
  KEY `schema_version_ir_idx` (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB ;

/*Table structure for table `teacher_profiles` */

DROP TABLE IF EXISTS `teacher_profiles`;

CREATE TABLE `teacher_profiles` (
  `userAccountId` bigint(20) NOT NULL,
  `certified` tinyint(1) NOT NULL DEFAULT '0',
  `yearsOfExperience` float DEFAULT NULL,
  `usptaCertified` tinyint(1) DEFAULT '0',
  `usptrCertified` tinyint(1) DEFAULT '0',
  `certifyingOrganization` varchar(255) DEFAULT NULL,
  `specialtyAdults` tinyint(1) DEFAULT '0',
  `specialtyJuniors` tinyint(1) DEFAULT '0',
  `specialtyTurnaments` tinyint(1) DEFAULT '0',
  `currency` varchar(3) DEFAULT NULL,
  `clinicRates` float DEFAULT NULL,
  `availableWeekendMorning` tinyint(1) DEFAULT '0',
  `availableWeekendAfternoon` tinyint(1) DEFAULT '0',
  `availableWeekendEvening` tinyint(1) DEFAULT '0',
  `availableWeekdayMorning` tinyint(1) DEFAULT '0',
  `availableWeekdayAfternoon` tinyint(1) DEFAULT '0',
  `availableWeekdayEvening` tinyint(1) DEFAULT '0',
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `profileImageFileId` bigint(20) DEFAULT NULL,
  `club` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userAccountId`),
  KEY `FK_TeacherProfiles_UserAccounts` (`userAccountId`),
  KEY `FK_teacherprofiles_imagefiles` (`profileImageFileId`),
  CONSTRAINT `FK_teacherprofiles_imagefiles` FOREIGN KEY (`profileImageFileId`) REFERENCES `image_files` (`imageFileId`),
  CONSTRAINT `FK_TeacherProfiles_UserAccounts` FOREIGN KEY (`userAccountId`) REFERENCES `user_accounts` (`userAccountId`)
) ENGINE=InnoDB ;

/*Table structure for table `tennis_centers` */

DROP TABLE IF EXISTS `tennis_centers`;

CREATE TABLE `tennis_centers` (
  `tennisCenterId` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `phoneNumber` varchar(30) DEFAULT NULL,
  `website` varchar(200) DEFAULT NULL,
  `imageFileId` bigint(20) DEFAULT NULL,
  `adminApproved` tinyint(1) DEFAULT '0',
  `description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`tennisCenterId`),
  KEY `FK_TennisCourts_ImageFiles` (`imageFileId`),
  KEY `FK_TennisCourts_Addresses` (`latitude`,`longitude`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;

/*Table structure for table `tennis_courts` */

DROP TABLE IF EXISTS `tennis_courts`;

CREATE TABLE `tennis_courts` (
  `tennisCourtId` bigint(20) NOT NULL AUTO_INCREMENT,
  `tennisCenterId` bigint(20) DEFAULT NULL,
  `numberOfCourts` int(11) DEFAULT NULL,
  `surface` varchar(10) DEFAULT NULL COMMENT '''CARPET'',''CLAY'',''GRASS'',''OTHER'',''HARD'',''SYNTHETIC''',
  `placement` varchar(7) DEFAULT NULL COMMENT '''OUTDOOR'',''INDOOR''',
  PRIMARY KEY (`tennisCourtId`),
  KEY `FK_TennisCourts_TennisCenters` (`tennisCenterId`),
  CONSTRAINT `FK_TennisCourts_TennisCenters` FOREIGN KEY (`tennisCenterId`) REFERENCES `tennis_centers` (`tennisCenterId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 ;

/*Table structure for table `user_accounts` */

DROP TABLE IF EXISTS `user_accounts`;

CREATE TABLE `user_accounts` (
  `userAccountId` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT 'all other fields are in the directory service',
  `password` varchar(255) DEFAULT NULL,
  `groupName` varchar(10) DEFAULT 'ROLE_USER',
  `createdOn` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `createdByIP` varchar(45) DEFAULT NULL,
  `modifiedByIP` varchar(45) DEFAULT NULL,
  `visibility` varchar(20) NOT NULL DEFAULT 'HIDE_EMAIL' COMMENT 'PUBLIC,PRIVATE, HIDE_EMAIL',
  `status` varchar(20) NOT NULL DEFAULT 'ACTIVE',
  `lastLogIn` datetime DEFAULT NULL,
  `loginCount` int(20) NOT NULL DEFAULT '0',
  `timezone` int(11) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `middleName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `addressLatitude` double DEFAULT NULL,
  `addressLongitude` double DEFAULT NULL,
  `gender` varchar(7) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `activationToken` varchar(50) DEFAULT NULL,
  `activationTokenExpires` datetime DEFAULT NULL,
  PRIMARY KEY (`userAccountId`),
  UNIQUE KEY `emailUniqueuIndex` (`email`),
  KEY `FK_UserAccounts_Addresses` (`addressLatitude`,`addressLongitude`),
  CONSTRAINT `FK_UserAccounts_Addresses` FOREIGN KEY (`addressLatitude`, `addressLongitude`) REFERENCES `addresses` (`latitude`, `longitude`)
) ENGINE=InnoDB AUTO_INCREMENT=210 ;

/*Table structure for table `user_posts` */

DROP TABLE IF EXISTS `user_posts`;

CREATE TABLE `user_posts` (
  `userPostId` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `imageFileId` bigint(20) DEFAULT NULL,
  `postedOn` datetime NOT NULL,
  PRIMARY KEY (`userPostId`)
) ENGINE=InnoDB ;
