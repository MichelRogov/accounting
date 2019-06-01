create table USER
(
    ID           bigint      not null auto_increment,
    FIRST_NAME   varchar(50),
    LAST_NAME    varchar(50),
    BIRTH_DATE   datetime          not null,
    EMAIL        varchar(50) not null,
    PHONE_NUMBER bigint          not null,
    CREATED_DATE datetime    not null,
    UPDATED_DATE datetime    not null,
    primary key (ID)
);

alter table USER
    add constraint USER_EMAIL unique (EMAIL);
