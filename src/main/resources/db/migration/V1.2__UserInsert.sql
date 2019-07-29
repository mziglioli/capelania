INSERT INTO `capelania`.`role` (id, name) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_ACTUATOR'),
(3, 'ROLE_USER');

INSERT INTO `capelania`.`user` (id, email, password, name) VALUES
(1, 'marceloziglioli@gmail', 'test', 'Admin');

insert into `capelania`.`user_role`(user_id, role_id) values
(1,1),
(1,2),
(1,3);