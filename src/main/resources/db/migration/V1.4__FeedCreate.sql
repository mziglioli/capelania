CREATE TABLE `capelania`.`feed` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  `header` VARCHAR(255) NULL,
  `text` text NOT NULL,
  `footer` VARCHAR(255) NULL,
  `status` VARCHAR(255) NULL DEFAULT 'CREATED',
  `active` TINYINT NULL DEFAULT 0,
  `date` timestamp NOT NULL,
  `expire` timestamp NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint(20) unsigned NOT NULL,
  `updated_date` timestamp NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` bigint(20) unsigned NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UNIQUE_FEED_title_date` (`title` ASC, `date` ASC));
