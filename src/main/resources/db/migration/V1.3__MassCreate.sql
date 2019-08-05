CREATE TABLE `capelania`.`mass` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  `day` VARCHAR(20) NULL,
  `start` VARCHAR(255) NULL,
  `duration` int NULL DEFAULT 60,
  `status` VARCHAR(255) NULL DEFAULT 'CREATED',
  `active` TINYINT NULL DEFAULT 0,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint(20) unsigned NOT NULL,
  `updated_date` timestamp NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` bigint(20) unsigned NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UNIQUE_MASS_DAY_TIME` (`day` ASC, `start` ASC));