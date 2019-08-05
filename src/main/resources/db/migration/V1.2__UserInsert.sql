INSERT INTO `capelania`.`role` (id, name) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_ACTUATOR'),
(3, 'ROLE_USER');

INSERT INTO `capelania`.`user` (`id`, `email`, `password`, `name`, `created_by`, `active`) VALUES
(1, 'marceloziglioli@gmail.com', '$2a$10$GZTDg/wIFoMjQSkj9R0yBOZfecSPFnANS3uoOuJ/zA9eyUdJGf8Dy', 'Admin', 1, 1);

INSERT INTO `capelania`.`user` (`id`, `email`, `password`, `name`, `created_by`, `active`) VALUES
(2, 'actuator@test.com', '$2a$10$GZTDg/wIFoMjQSkj9R0yBOZfecSPFnANS3uoOuJ/zA9eyUdJGf8Dy', 'Actuator', 1, 1);
INSERT INTO `capelania`.`user` (`id`, `email`, `password`, `name`, `created_by`, `active`) VALUES
(3, 'user@test.com', '$2a$10$GZTDg/wIFoMjQSkj9R0yBOZfecSPFnANS3uoOuJ/zA9eyUdJGf8Dy', 'User', 1, 1);

insert into `capelania`.`user_role`(user_id, role_id) values
(1,1),
(1,2),
(1,3),
(2,2),
(2,3),
(3,3);