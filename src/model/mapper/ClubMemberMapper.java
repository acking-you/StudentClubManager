package model.mapper;

import model.entity.ClubMember;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClubMemberMapper {
    @Insert("INSERT INTO club_members(`student_id`,`name`,`sex`,`age`,`major`,`interest`) " +
            "VALUES(#{member.studentId},#{member.name},#{member.sex},#{member.age},#{member.major},#{member.interest});")
    void addClubMember(@Param("member") ClubMember member);

    @Select("SELECT c.id FROM club_members c WHERE c.student_id=#{studentId}")
    int queryIdFromStudentId(String studentId);

    @Update("update club_members set " +
            "student_id=#{studentId},name=#{name}," +
            "sex=#{sex},age=#{age},major=#{major},interest=#{interest} " +
            "where id=#{id}")
    void updateClubMember(ClubMember member);

    //模糊查询返回对应的成员
    @Select("SELECT c.* FROM club_member_relations r, club_members c " +
            "WHERE r.club_member_id = c.id AND r.club_id=#{clubId} AND c.`name` LIKE '%${name}%';")
    List<ClubMember> queryClubMemberByName(@Param("clubId") int clubId, @Param("name") String name);

    @Select("SELECT c.* FROM club_member_relations r, club_members c " +
            "WHERE r.club_id=#{clubId} AND c.id = r.club_member_id;")
    List<ClubMember> queryClubMemberByClubId(int clubId);

    @Delete("DELETE FROM club_members WHERE club_members.id = #{id};")
    void deleteClubMemberById(int id);

    @Select("SELECT COUNT(*) FROM club_members WHERE id = #{memberId};")
    int isClubMemBerExistById(int memberId);
}
