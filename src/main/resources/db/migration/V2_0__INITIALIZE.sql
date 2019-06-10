create table STUDENT_GROUP
(
    ID         bigint   not null auto_increment,
    START_DATE datetime not null,
    MODULE_ID  bigint   not null,
    primary key (ID)
);

create table STUDENT_GROUP_USER
(
    USER_ID          bigint,
    STUDENT_GROUP_ID bigint not null
);


create table LESSON
(
    ID               bigint       not null auto_increment,
    LESSON_DATE      datetime     not null,
    THEMA            varchar(255) not null,
    STUDENT_GROUP_ID bigint       not null,
    SUBJECT_ID       bigint       not null,
    TEACHER_ID       bigint       not null,
    primary key (ID)
);

create table MODULE
(
    ID    bigint       not null auto_increment,
    HOURS integer      not null,
    NAME  varchar(255) not null,
    PRICE double precision,
    primary key (ID)
);

create table SUBJECT
(
    ID   bigint not null auto_increment,
    NAME varchar(255),
    primary key (ID)
);

create table MODULE_SUBJECT
(
    MODULE_ID  bigint not null,
    SUBJECT_ID bigint not null
);
create table ATTENDANCE
(
    ID         bigint not null auto_increment,
    IS_PRESENT bit,
    LESSON_ID  bigint not null,
    USER_ID    bigint not null,
    primary key (ID)
);


alter table ATTENDANCE
    add constraint ATTENDANCE_LESSON_ID_LESSON_ID foreign key (LESSON_ID) references LESSON (ID);
alter table ATTENDANCE
    add constraint ATENDANCE_USER_ID_USER_ID foreign key (USER_ID) references USER (ID);
alter table STUDENT_GROUP_USER
    add constraint STUDENT_GROUP_USER_USER_ID_USER_ID foreign key (USER_ID) references USER (ID);
alter table STUDENT_GROUP_USER
    add constraint GROUP_USER_GROUP_ID_GROUP_ID foreign key (STUDENT_GROUP_ID) references STUDENT_GROUP (ID);
alter table STUDENT_GROUP
    add constraint STUDENT_GROUP_MODULE_ID_MODULE_ID foreign key (MODULE_ID) references MODULE (ID);
alter table LESSON
    add constraint LESSON_GROUP_ID_GROUP_ID foreign key (STUDENT_GROUP_ID) references STUDENT_GROUP (ID);
alter table LESSON
    add constraint LESSON_SUBJECT_ID_SUBJECT_ID foreign key (SUBJECT_ID) references SUBJECT (ID);
alter table LESSON
    add constraint LESSON_TEACHER_ID_USER_ID foreign key (TEACHER_ID) references USER (ID);
alter table MODULE_SUBJECT
    add constraint MODULE_SUBJECT_SUBJECT_ID_SUBJECT_ID foreign key (SUBJECT_ID) references SUBJECT (ID);
alter table MODULE_SUBJECT
    add constraint MODULE_SUBJECT_MODULE_ID_MODULE_ID foreign key (MODULE_ID) references MODULE (ID);
