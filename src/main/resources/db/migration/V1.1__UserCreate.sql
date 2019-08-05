CREATE TABLE `capelania`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `status` VARCHAR(255) NULL DEFAULT 'CREATED',
  `active` TINYINT NULL DEFAULT 0,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint(20) unsigned NOT NULL,
  `updated_date` timestamp NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` bigint(20) unsigned NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UNIQUE_USER_EMAIL` (`email` ASC));

CREATE TABLE `capelania`.`role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `status` VARCHAR(255) NULL DEFAULT 'CREATED',
  `active` TINYINT NULL DEFAULT 1,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint(20) unsigned NULL DEFAULT 1,
  `updated_date` timestamp NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` bigint(20) unsigned NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UNIQUE_ROLE_NAME` (`name` ASC));

CREATE TABLE `capelania`.`user_role` (
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `FK_USER_ROLE_ROLE_ID_idx` (`role_id` ASC),
  CONSTRAINT `FK_USER_ROLE_USER_ID`
    FOREIGN KEY (`user_id`)
    REFERENCES `capelania`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_ROLE_ROLE_ID`
    FOREIGN KEY (`role_id`)
    REFERENCES `capelania`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
