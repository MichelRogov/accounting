create table USER_ACCOUNT
(
ID           bigint  not null_auto_increment,
NAME         varchar(50),
EMAIL        varchar(50) not null,
PASSWORD     varchar(50) not null,
CREATED_DATE datetime    not null,
UPDATED_DATE datetime    not null,
primary key(ID)
);
