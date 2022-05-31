create table student_system.club_member_relations
(
    club_id bigint unsigned not null,
    club_member_id bigint unsigned not null,
    primary key (club_member_id, club_id),
    constraint fk_club_member_relations_club
        foreign key (club_id) references student_system.clubs (id),
    constraint fk_club_member_relations_club_member
        foreign key (club_member_id) references student_system.club_members (id)
);