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
    add constraint USER_ID unique (ID);
alter table USER
    add constraint USER_FIRST_NAME unique (FIRST_NAME);
alter table USER
    add constraint USER_LAST_NAME unique (LAST_NAME);
alter table USER
    add constraint USER_BIRTH_DATE unique (BIRTH_DATE);
alter table USER
    add constraint USER_EMAIL unique (EMAIL);
alter table USER
    add constraint USER_PHONE_NUMBER unique (PHONE_NUMBER);
alter table USER
    add constraint USER_CREATED_DATE unique (CREATED_DATE);
alter table USER
    add constraint USER_UPDATED_DATE unique (UPDATED_DATE);




