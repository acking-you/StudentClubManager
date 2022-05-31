create table student_system.clubs
(
    id bigint unsigned auto_increment
        primary key,
    member_count bigint unsigned null,
    category varchar(50) null,
    name varchar(50) null
);