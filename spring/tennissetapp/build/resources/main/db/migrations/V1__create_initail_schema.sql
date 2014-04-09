-- CREATE DATABASE `tennissetapp` DEFAULT CHARACTER SET utf8;
USE `tennissetapp`;

/*Table structure for table `UserConnection` */

CREATE TABLE `UserConnection` (
  `userId` VARCHAR(255) NOT NULL,
  `providerId` VARCHAR(255) NOT NULL,
  `providerUserId` VARCHAR(255) NOT NULL DEFAULT '',
  `rank` INT(11) NOT NULL,
  `displayName` VARCHAR(255) DEFAULT NULL,
  `profileUrl` VARCHAR(512) DEFAULT NULL,
  `imageUrl` VARCHAR(512) DEFAULT NULL,
  `accessToken` VARCHAR(255) NOT NULL,
  `secret` VARCHAR(255) DEFAULT NULL,
  `refreshToken` VARCHAR(255) DEFAULT NULL,
  `expireTime` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=INNODB;

CREATE TABLE `addresses` (
  `latitude` DOUBLE NOT NULL DEFAULT '0',
  `longitude` DOUBLE NOT NULL DEFAULT '0',
  `streetNumber` VARCHAR(50) CHARACTER SET latin1 DEFAULT NULL,
  `route` VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
  `routeShortName` VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
  `locality` VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
  `localityShortName` VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
  `sublocality` VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
  `sublocalityShortName` VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
  `administrativeAreaLevel2` VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
  `administrativeAreaLevel2ShortName` VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
  `administrativeAreaLevel1` VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
  `administrativeAreaLevel1ShortName` VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
  `country` VARCHAR(50) CHARACTER SET latin1 DEFAULT NULL,
  `countryShortName` VARCHAR(3) CHARACTER SET latin1 DEFAULT NULL,
  `postalCode` VARCHAR(10) CHARACTER SET latin1 DEFAULT NULL,
  `createdOn` DATETIME DEFAULT NULL,
  `modifiedOn` DATETIME DEFAULT NULL,
  PRIMARY KEY (`latitude`,`longitude`)
) ENGINE=INNODB;

CREATE TABLE `user_accounts` (
  `userAccountId` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'all other fields are in the directory service',
  `password` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `groupName` VARCHAR(10) COLLATE utf8_unicode_ci DEFAULT 'ROLE_USER',
  `createdOn` DATETIME NOT NULL,
  `email` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `createdByIP` VARCHAR(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifiedByIP` VARCHAR(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `visibility` VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'HIDE_EMAIL' COMMENT 'PUBLIC,PRIVATE, HIDE_EMAIL',
  `status` VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'ACTIVE',
  `lastLogIn` DATETIME DEFAULT NULL,
  `loginCount` INT(20) NOT NULL DEFAULT '0',
  `timezone` INT(11) DEFAULT NULL,
  `firstName` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `middleName` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastName` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `addressLatitude` DOUBLE DEFAULT NULL,
  `addressLongitude` DOUBLE DEFAULT NULL,
  `gender` VARCHAR(7) COLLATE utf8_unicode_ci DEFAULT NULL,
  `aboutMe` VARCHAR(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthDate` DATE DEFAULT NULL,
  `profileImageFileId` BIGINT(20) DEFAULT NULL,
  `activationToken` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `activationTokenExpires` DATETIME DEFAULT NULL,
  PRIMARY KEY (`userAccountId`),
  UNIQUE KEY `emailUniqueuIndex` (`email`),
  KEY `FK_UserAccounts_Addresses` (`addressLatitude`,`addressLongitude`),
  CONSTRAINT `FK_UserAccounts_Addresses` FOREIGN KEY (`addressLatitude`, `addressLongitude`) REFERENCES `addresses` (`latitude`, `longitude`)
) ENGINE=INNODB;

CREATE TABLE `image_files` (
  `imageFileId` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `createdOn` DATETIME DEFAULT NULL,
  `width` INT(11) DEFAULT NULL COMMENT 'in pixels',
  `height` INT(11) DEFAULT NULL COMMENT 'in pixels',
  `size` INT(11) DEFAULT NULL COMMENT 'in bytes',
  `format` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fileName` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ownerId` BIGINT(20) DEFAULT NULL,
  `dirPath` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'USER_DEFAULT, GARBAGE, folder management, user will be able to create folders',
  `album` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT 'Default',
  PRIMARY KEY (`imageFileId`),
  KEY `FK_ImageFiles_UserAccounts` (`ownerId`),
  CONSTRAINT `FK_ImageFiles_UserAccounts` FOREIGN KEY (`ownerId`) REFERENCES `user_accounts` (`userAccountId`)
) ENGINE=INNODB;

CREATE TABLE `player_profiles` (
  `profileId` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `userAccountId` BIGINT(20) NOT NULL,
  `createdOn` DATETIME DEFAULT NULL,
  `createdByIP` VARCHAR(20) CHARACTER SET latin1 DEFAULT NULL,
  `modifiedByIP` VARCHAR(20) CHARACTER SET latin1 DEFAULT NULL,
  `profileImageFileId` BIGINT(20) DEFAULT NULL,
  `aboutMe` VARCHAR(1000) CHARACTER SET latin1 DEFAULT NULL,
  `birthDate` DATE DEFAULT NULL,
  `levelOfPlay` FLOAT DEFAULT NULL,
  `hand` VARCHAR(8) CHARACTER SET latin1 DEFAULT NULL,
  `playSingles` TINYINT(1) DEFAULT '0',
  `playDoubles` TINYINT(1) DEFAULT '0',
  `playFullMatch` TINYINT(1) DEFAULT '0',
  `playPoints` TINYINT(1) DEFAULT '0',
  `playHittingAround` TINYINT(1) DEFAULT '0',
  `availableWeekendMorning` TINYINT(1) DEFAULT '0',
  `availableWeekendAfternoon` TINYINT(1) DEFAULT '0',
  `availableWeekendEvening` TINYINT(1) DEFAULT '0',
  `availableWeekdayMorning` TINYINT(1) DEFAULT '0',
  `availableWeekdayAfternoon` TINYINT(1) DEFAULT '0',
  `availableWeekdayEvening` TINYINT(1) DEFAULT '0',
  PRIMARY KEY (`profileId`),
  KEY `FK_UserProfile_ImageFiles` (`profileImageFileId`),
  KEY `FK_PlayerProfiles_UserAccounts` (`userAccountId`),
  CONSTRAINT `FK_PlayerProfiles_UserAccounts` FOREIGN KEY (`userAccountId`) REFERENCES `user_accounts` (`userAccountId`)
) ENGINE=INNODB;

CREATE TABLE `teacher_profiles` (
  `profileId` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `userAccountId` BIGINT(20) NOT NULL,
  `certified` TINYINT(1) NOT NULL DEFAULT '0',
  `yearsOfExperience` FLOAT DEFAULT NULL,
  `usptaCertified` TINYINT(1) DEFAULT '0',
  `usptrCertified` TINYINT(1) DEFAULT '0',
  `certifyingOrganization` VARCHAR(255) DEFAULT NULL,
  `specialityAdults` TINYINT(1) DEFAULT '0',
  `specialityJuniors` TINYINT(1) DEFAULT '0',
  `specialityTurnaments` TINYINT(1) DEFAULT '0',
  `currency` VARCHAR(3) DEFAULT NULL,
  `clinicRates` FLOAT DEFAULT NULL,
  `availableWeekendMorning` TINYINT(1) DEFAULT '0',
  `availableWeekendAfternoon` TINYINT(1) DEFAULT '0',
  `availableWeekendEvening` TINYINT(1) DEFAULT '0',
  `availableWeekdayMorning` TINYINT(1) DEFAULT '0',
  `availableWeekdayAfternoon` TINYINT(1) DEFAULT '0',
  `availableWeekdayEvening` TINYINT(1) DEFAULT '0',
  `createdOn` DATETIME DEFAULT NULL,
  `modifiedOn` DATETIME DEFAULT NULL,
  `profileImageFileId` BIGINT(20) DEFAULT NULL,
  `club` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`profileId`,`certified`),
  KEY `FK_TeacherProfiles_UserAccounts` (`userAccountId`),
  CONSTRAINT `FK_TeacherProfiles_UserAccounts` FOREIGN KEY (`userAccountId`) REFERENCES `user_accounts` (`userAccountId`)
) ENGINE=INNODB;

CREATE TABLE `tennis_centers` (
  `tennisCenterId` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `latitude` DOUBLE DEFAULT NULL,
  `longitude` DOUBLE DEFAULT NULL,
  `phoneNumber` VARCHAR(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `website` VARCHAR(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imageFileId` BIGINT(20) DEFAULT NULL,
  `adminApproved` TINYINT(1) DEFAULT '0',
  `description` TEXT COLLATE utf8_unicode_ci,
  `createdOn` DATETIME DEFAULT NULL,
  `modifiedOn` DATETIME DEFAULT NULL,
  PRIMARY KEY (`tennisCenterId`),
  KEY `FK_TennisCourts_ImageFiles` (`imageFileId`),
  KEY `FK_TennisCourts_Addresses` (`latitude`,`longitude`)
) ENGINE=INNODB;

CREATE TABLE `tennis_courts` (
  `tennisCourtId` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `tennisCenterId` BIGINT(20) DEFAULT NULL,
  `numberOfCourts` INT(11) DEFAULT NULL,
  `surface` VARCHAR(10) DEFAULT NULL COMMENT '''CARPET'',''CLAY'',''GRASS'',''OTHER'',''HARD'',''SYNTHETIC''',
  `placement` VARCHAR(7) DEFAULT NULL COMMENT '''OUTDOOR'',''INDOOR''',
  PRIMARY KEY (`tennisCourtId`),
  KEY `FK_TennisCourts_TennisCenters` (`tennisCenterId`),
  CONSTRAINT `FK_TennisCourts_TennisCenters` FOREIGN KEY (`tennisCenterId`) REFERENCES `tennis_centers` (`tennisCenterId`)
) ENGINE=INNODB;

CREATE TABLE `user_posts` (
  `userPostId` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(200) CHARACTER SET latin1 NOT NULL,
  `content` VARCHAR(1024) CHARACTER SET latin1 DEFAULT NULL,
  `imageFileId` BIGINT(20) DEFAULT NULL,
  `postedOn` DATETIME NOT NULL,
  PRIMARY KEY (`userPostId`)
) ENGINE=INNODB;

CREATE TABLE `matches` (
  `matchId` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  `levelOfPlayMin` FLOAT NOT NULL,
  `levelOfPlayMax` FLOAT NOT NULL,
  `visibility` VARCHAR(20) NOT NULL,
  `playSingles` TINYINT(1) DEFAULT '0',
  `playDoubles` TINYINT(1) DEFAULT '0',
  `playFullMatch` TINYINT(1) DEFAULT '0',
  `playPoints` TINYINT(1) DEFAULT '0',
  `playHittingAround` TINYINT(1) DEFAULT '0',
  `tennisCenterId` BIGINT(20) NOT NULL,
  `orgenizerId` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`matchId`),
  KEY `FK_Matches_TennisCenters` (`tennisCenterId`),
  KEY `FK_Matches_UserAccounts` (`orgenizerId`),
  CONSTRAINT `FK_Matches_TennisCenters` FOREIGN KEY (`tennisCenterId`) REFERENCES `tennis_centers` (`tennisCenterId`),
  CONSTRAINT `FK_Matches_UserAccounts` FOREIGN KEY (`orgenizerId`) REFERENCES `user_accounts` (`userAccountId`)
) ENGINE=INNODB;

CREATE TABLE `playerprofile_tenniscenter` (
  `profileId` BIGINT(20) NOT NULL,
  `tennisCenterId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`profileId`,`tennisCenterId`),
  KEY `FK_UserAccount_TennisCourt_TennisCourts` (`tennisCenterId`),
  CONSTRAINT `FK_PlayerProfile_TennisCenter_PlayerProfiles` FOREIGN KEY (`profileId`) REFERENCES `player_profiles` (`profileId`),
  CONSTRAINT `FK_PlayerProfile_TennisCenter_TennisCenters` FOREIGN KEY (`tennisCenterId`) REFERENCES `tennis_centers` (`tennisCenterId`)
) ENGINE=INNODB;

/*------------------FUNCTIONS------------------*/

/* Function  structure for function  `availabilityWeight` */

DELIMITER $$
CREATE FUNCTION `availabilityWeight`() RETURNS DOUBLE
BEGIN
		RETURN 0;
END $$
DELIMITER ;

/* Function  structure for function  `distanceWeight` */

DELIMITER $$
CREATE FUNCTION `distanceWeight`(centerLat DOUBLE, centerLng DOUBLE, latitude DOUBLE, longitude DOUBLE) RETURNS DOUBLE
BEGIN
		SET @weight = 0;
		SET @distance = geoDistance(centerLat,centerLng,latitude,longitude);
		
		SET @weight = IF(@distance < 10,3,
			IF(@distance < 20,2, 
				IF(@distance < 50, 1,0)
			)
		);
		RETURN @weight;
    END $$
DELIMITER ;

/* Function  structure for function  `geoDistance` */

DELIMITER $$

CREATE FUNCTION `geoDistance`(lat1 DOUBLE, lng1 DOUBLE, lat2 DOUBLE, lng2 DOUBLE) RETURNS DOUBLE
BEGIN
		RETURN SQRT(POW(111 * (lat1 - lat2), 2) + POW(111 * (lng2 - lng1) * COS(lat1 / 57.29577951308232), 2));
    END $$
DELIMITER ;

/* Function  structure for function  `playerLevelWeight` */

DELIMITER $$

CREATE FUNCTION `playerLevelWeight`(playerLevel FLOAT, levelMin FLOAT, levelMax FLOAT) RETURNS DOUBLE
BEGIN
		SET @difference = LEAST(ABS(playerLevel - levelMin),ABS(playerLevel - levelMax));
		
		SET @weight = IF( @difference < 1,4,
			IF(@difference <= 2, 3,
				IF(@difference <= 3, 2,
					IF(@difference <= 4, 1,0)
				)
			)
		);
		RETURN @weight;
    END $$
DELIMITER ;

/* Function  structure for function  `typeOfPlayWeight` */

DELIMITER $$

CREATE FUNCTION `typeOfPlayWeight`(
		playSingles BOOL, playDoubles BOOL, playPoints BOOL, playFullMatch BOOL,playHittingAround BOOL,
		matchSingles BOOL, matchDoubles BOOL, matchPoints BOOL, matchFullMatch BOOL, matchHittingAround BOOL) RETURNS DOUBLE
BEGIN
		SET @count = (playSingles & matchSingles) +
		(playDoubles & matchDoubles) +
		(playPoints & matchPoints) +
		(playHittingAround & matchHittingAround) +
		(playFullMatch & matchFullMatch);
		
		RETURN IF(@count >= 1, 1,0);
    END $$
DELIMITER ;

/* ---------------------------PROCEDURES--------------------------- */

DELIMITER $$

USE `tennissetapp`$$

CREATE PROCEDURE `clear`()
BEGIN
DELETE FROM PlayerProfile_TennisCourt;
DELETE FROM UserAccounts;
DELETE FROM PlayerProfiles;
DELETE FROM TeacherProfiles;
DELETE FROM ImageFiles;
    END$$

DELIMITER ;

/*--*/

DELIMITER $$

USE `tennissetapp`$$

CREATE PROCEDURE `countSearchMatches`(
latitude DOUBLE,
longitude DOUBLE, 
distance DOUBLE)
BEGIN
	SELECT COUNT(u.userAccountId)
		FROM matches AS m
		INNER JOIN tennis_centers AS c ON m.tennisCenterId = c.tennisCenterId
		INNER JOIN addresses AS a ON c.latitude = a.latitude AND c.longitude = a.longitude
		INNER JOIN user_accounts AS u ON u.userAccountId = m.orgenizerId
		WHERE geoDistance(c.latitude,c.longitude,latitude,longitude) <= distance;
   END$$

DELIMITER ;

/*--*/

DELIMITER $$

USE `tennissetapp`$$

CREATE PROCEDURE `countSearchMates`(latitude DOUBLE, longitude DOUBLE, distance DOUBLE)
BEGIN
	SELECT COUNT(u.userAccountId) AS `count`
	FROM user_accounts AS u
	WHERE geoDistance(u.addressLatitude,u.addressLongitude,latitude,longitude) <= distance;
END$$

DELIMITER ;

/*--*/

DELIMITER $$

USE `tennissetapp`$$

CREATE PROCEDURE `countSearchMatesByUser`(
`user` VARCHAR(255)
)
BEGIN
SELECT COUNT(u.userAccountId)
	FROM user_accounts AS u 
	INNER JOIN player_profiles AS p ON u.userAccountId = p.userAccountId
	INNER JOIN addresses AS a ON u.addressLatitude = a.latitude AND u.addressLongitude = a.longitude
	LEFT JOIN image_files AS i ON u.profileImageFileId = i.imageFileId
	WHERE `user` LIKE CONCAT('%',u.firstName,'%') OR `user` LIKE CONCAT('%',u.lastName,'%') OR u.username = `user` OR u.email = `user`;
		
END$$

DELIMITER ;

/*--*/

DELIMITER $$

USE `tennissetapp`$$

CREATE PROCEDURE `searchMatches`(
latitude DOUBLE,
longitude DOUBLE, 
distance DOUBLE,
playerLevel FLOAT,
playSingles BOOL,
playDoubles BOOL,
playPoints BOOL,
playFullMatch BOOL,
playHittingAround BOOL, 
firstResult INT, 
maxResults INT)
BEGIN
	SELECT m.matchId, m.name, m.playSingles, m.playDoubles, m.playFullMatch, m.playPoints,
		u.firstName AS orgenizerFirstName, u.lastName AS orgenizerLastName,
		c.name AS tennisCenterName, a.localityShortName AS address,
		m.levelOfPlayMin, m.levelOfPlayMax, m.start,
		m.playHittingAround, m.visibility, a.latitude, a.longitude,
		(	
			distanceWeight(a.latitude, a.longitude, latitude,longitude) + 
			playerLevelWeight(playerLevel,m.levelOfPlayMin , m.levelOfPlayMax) + 
			typeOfPlayWeight(playSingles,playDoubles,playPoints,playFullMatch,playHittingAround,
			m.playSingles,m.playDoubles,m.playPoints,m.playFullMatch,m.playHittingAround) +
			availabilityWeight()
		) AS weight
		
		FROM matches AS m
		INNER JOIN tennis_centers AS c ON m.tennisCenterId = c.tennisCenterId
		INNER JOIN addresses AS a ON c.latitude = a.latitude AND c.longitude = a.longitude
		INNER JOIN user_accounts AS u ON u.userAccountId = m.orgenizerId
		WHERE geoDistance(c.latitude,c.longitude,latitude,longitude) <= distance
		ORDER BY weight DESC
		LIMIT firstResult,maxResults;
    END$$

DELIMITER ;

/*--*/

DELIMITER $$

USE `tennissetapp`$$

CREATE PROCEDURE `searchMates`(
latitude DOUBLE,
longitude DOUBLE,
distance DOUBLE,
levelOfPlayMin FLOAT,
levelOfPlayMax FLOAT,
playSingles BOOL,
playDoubles BOOL,
playPoints BOOL,
playFullMatch BOOL,
playHittingAround BOOL, 
firstResult INT, 
maxResults INT
)
BEGIN
	SELECT u.userAccountId,u.firstName,u.lastName,p.playSingles,p.playDoubles,p.playFullMatch,p.playPoints,
	p.playHittingAround,p.availableWeekendMorning,p.availableWeekendEvening,p.availableWeekendAfternoon,
	p.availableWeekdayMorning,p.availableWeekdayAfternoon,p.availableWeekdayEvening,p.levelOfPlay,
	i.fileName AS profilePhoto,
	CONCAT(a.administrativeAreaLevel1,', ', a.country) AS address,
	(	
		distanceWeight(u.addressLatitude, u.addressLongitude, latitude,longitude) + 
		playerLevelWeight(p.levelOfPlay,levelOfPlayMin , levelOfPlayMax) + 
		typeOfPlayWeight(playSingles,playDoubles,playPoints,playFullMatch,playHittingAround,
		p.playSingles,p.playDoubles,p.playPoints,p.playFullMatch,p.playHittingAround) +
		availabilityWeight()
	) AS weight
	FROM user_accounts AS u 
	INNER JOIN player_profiles AS p ON u.userAccountId = p.userAccountId
	INNER JOIN addresses AS a ON u.addressLatitude = a.latitude AND u.addressLongitude = a.longitude
	LEFT JOIN image_files AS i ON u.profileImageFileId = i.imageFileId
	WHERE geoDistance(u.addressLatitude,u.addressLongitude,latitude,longitude) <= distance
	ORDER BY weight DESC
	LIMIT firstResult,maxResults;
END$$

DELIMITER ;

/*--*/

DELIMITER $$

USE `tennissetapp`$$

CREATE PROCEDURE `searchMatesByUser`(
`user` VARCHAR(255),
latitude DOUBLE,
longitude DOUBLE,
levelOfPlayMin FLOAT,
levelOfPlayMax FLOAT,
playSingles BOOL,
playDoubles BOOL,
playPoints BOOL,
playFullMatch BOOL,
playHittingAround BOOL, 
firstResult INT, 
maxResults INT    
    )
BEGIN
SELECT u.userAccountId,u.firstName,u.lastName,p.playSingles,p.playDoubles,p.playFullMatch,p.playPoints,
	p.playHittingAround,p.availableWeekendMorning,p.availableWeekendEvening,p.availableWeekendAfternoon,
	p.availableWeekdayMorning,p.availableWeekdayAfternoon,p.availableWeekdayEvening,p.levelOfPlay,
	i.fileName AS profilePhoto,
	CONCAT(a.administrativeAreaLevel1,', ', a.country) AS address,
	(	
		distanceWeight(u.addressLatitude, u.addressLongitude, latitude,longitude) + 
		playerLevelWeight(p.levelOfPlay,levelOfPlayMin , levelOfPlayMax) + 
		typeOfPlayWeight(playSingles,playDoubles,playPoints,playFullMatch,playHittingAround,
		p.playSingles,p.playDoubles,p.playPoints,p.playFullMatch,p.playHittingAround) +
		availabilityWeight()
	) AS weight
	FROM user_accounts AS u 
	INNER JOIN player_profiles AS p ON u.userAccountId = p.userAccountId
	INNER JOIN addresses AS a ON u.addressLatitude = a.latitude AND u.addressLongitude = a.longitude
	LEFT JOIN image_files AS i ON u.profileImageFileId = i.imageFileId
	WHERE `user` LIKE CONCAT('%',u.firstName,'%') OR `user` LIKE CONCAT('%',u.lastName,'%') OR u.username = `user` OR u.email = `user`
	ORDER BY weight DESC
	LIMIT firstResult,maxResults;
    END$$

DELIMITER ;


DELIMITER $$
USE `tennissetapp`$$
CREATE PROCEDURE `countTennisCenters`(latitude DOUBLE, longitude DOUBLE, maxDistance DOUBLE)
BEGIN
SELECT COUNT(c.tennisCenterId) 
FROM addresses AS a INNER JOIN tennis_centers AS c ON c.latitude = a.latitude AND c.longitude = a.longitude 
WHERE geoDistance(a.latitude,a.longitude,latitude,longitude) <= maxDistance;
    END$$
DELIMITER ;


DELIMITER $$
USE `tennissetapp`$$
CREATE PROCEDURE `scrollTennisCenters`(latitude DOUBLE, longitude DOUBLE, maxDistance DOUBLE, firstResult INT, maxResults INT)
BEGIN
		SELECT c.tennisCenterId, c.name, a.route, a.locality, a.administrativeAreaLevel1, 
geoDistance(a.latitude,a.longitude,latitude,longitude) AS distance, 
CONCAT(a.localityShortName,', ',a.administrativeAreaLevel1ShortName) AS address, c.phoneNumber 
FROM addresses AS a INNER JOIN tennis_centers AS c ON c.latitude = a.latitude AND c.longitude = a.longitude 
HAVING distance <= maxDistance
ORDER BY distance 
LIMIT firstResult,maxResults;
    END$$
DELIMITER ;