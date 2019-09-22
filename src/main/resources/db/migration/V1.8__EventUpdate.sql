INSERT INTO  `capelania`.`event` (`id`,`title`,`description`,`text`,`date`,`img`,`created_by`,`active`)
VALUES (2,'Galinhada','Galinhada comunitária','Segundo almoco efetuado pela capelania, com o intuito de aproximar ainda mais os fieis em Cristo.','2019-09-01 11:00', 'https://capelania-images.s3.eu-west-2.amazonaws.com/events/galinhada.jpeg',1,1);

INSERT INTO  `capelania`.`event` (`id`,`title`,`description`,`text`,`date`,`img`,`created_by`,`active`)
VALUES (3,'Strogonoff','Strogonoff comunitária','Terceiro almoco efetuado pela capelania, com o intuito de aproximar ainda mais os fieis em Cristo.','2019-09-22 11:00','https://capelania-images.s3.eu-west-2.amazonaws.com/events/strogonoff.jpeg', 1,1);

UPDATE `capelania`.`event` SET `date` = '2019-07-21 11:00' WHERE (`id` = '1');
UPDATE `capelania`.`event` SET `img` = 'https://capelania-images.s3.eu-west-2.amazonaws.com/events/feijoada.jpg' WHERE (`id` = '1');