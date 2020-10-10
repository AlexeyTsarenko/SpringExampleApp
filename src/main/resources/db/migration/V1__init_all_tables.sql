CREATE TABLE `users`
(
    `id`     integer     NOT NULL AUTO_INCREMENT,
    `email`   varchar(50)     NOT NULL,
    `password`   varchar(50)   NOT NULL,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `age` integer     NOT NULL,
    `date` date     NOT NULL,


    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;
