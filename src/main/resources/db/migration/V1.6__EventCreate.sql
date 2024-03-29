CREATE TABLE `capelania`.`event` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  `text` text NOT NULL,
  `status` VARCHAR(255) NULL DEFAULT 'CREATED',
  `img` VARCHAR(255) NULL DEFAULT 'https://upload.wikimedia.org/wikipedia/commons/6/6c/No_image_3x4.svg',
  `active` TINYINT NULL DEFAULT 0,
  `date` VARCHAR(255) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint(20) unsigned NOT NULL,
  `updated_date` timestamp NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` bigint(20) unsigned NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UNIQUE_FEED_title_date` (`title` ASC, `date` ASC));
