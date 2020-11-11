CREATE TABLE `users`
(
    id         bigint       NOT NULL AUTO_INCREMENT,
    email      varchar(50)  NOT NULL,
    password   varchar(100) NOT NULL,
    first_name varchar(50)  NOT NULL,
    last_name  varchar(50)  NOT NULL,
    birth_date date         NOT NULL,
    created    timestamp    NOT NULL,
    updated    timestamp    NOT NULL,
    status     varchar(50)  NOT NULL,

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = latin1;
CREATE TABLE `roles`
(
    id   bigint      NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    -- created timestamp   NOT NULL,
    -- updated timestamp   NOT NULL,
    -- status  varchar(50) NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;
create table users_roles
(
    user_id BIGINT not null,
    role_id BIGINT not null,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);
insert into roles
values (default, 'ROLE_USER'),
       (default, 'ROLE_ADMIN');

CREATE TABLE `history`
(
    id          bigint      NOT NULL AUTO_INCREMENT,
    entity_id   bigint(50)  NOT NULL,
    column_name varchar(50) NOT NULL,
    old_value   varchar(50) NOT NULL,
    new_value   varchar(50) NOT NULL,
    created     timestamp   NOT NULL,
    updated     timestamp   NOT NULL,
    status      varchar(50) NOT NULL,

    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;