create table student_system.club_members
(
    id bigint unsigned auto_increment
        primary key,
    student_id varchar(20) not null,
    name varchar(50) not null,
    sex enum('ÄÐ', 'Å®') charset utf8mb4 default 'ÄÐ' not null,
    age bigint unsigned not null,
    major varchar(100) not null,
    interest varchar(100) not null
);

