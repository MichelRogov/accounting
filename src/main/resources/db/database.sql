-- Table users
create table users
(
    ID                   int    not null auto_increment primary key,
    USERNAME                VARCHAR(255) NOT NULL,
    PASSWORD                VARCHAR(255) NOT NULL

) ENGINE = InnoDB;

-- Table roles
create table roles
(
    ID                   int    not null auto_increment primary key,
    NAME                 varchar (100)  not null
)
    ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
create table user_roles
(
    USER_ID                int        not null ,
    ROLES_ID               int        not null,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (roles_id) REFERENCES roles (id),

    UNIQUE (user_id, roles_id)
)
    ENGINE = InnoDB;

-- insert data

INSERT INTO users VALUES (1, 'proselyte', '12345678');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT  INTO user_roles VALUES (1, 2);


