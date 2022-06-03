package model.mapper;

import model.entity.Club;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ClubMapper {
    @Insert("insert into clubs (`member_count`,`category`,`name`)  values" +
            "(#{memberCount},#{category},#{name})")
    void addClub(Club club);

    @Update("update clubs set " +
            "member_count=#{club.memberCount},category=#{club.category}," +
            "name=#{club.name} where id=#{club.id}")
    void updateClub(@Param("club") Club club);

    @Update("UPDATE clubs SET member_count = member_count+${increment} WHERE id=#{id};")
    void plusMemberCountById(@Param("id") int id, @Param("increment") int increment);

    @Update("UPDATE clubs SET member_count = member_count-1 WHERE id=#{id};")
    void minusMemberCountById(int id);

    @Select("SELECT * FROM clubs WHERE `name` LIKE '%${name}%';")
    List<Club> queryClubsByName(String name);

    @Select("SELECT c.* FROM club_member_relations r, clubs c " +
            "WHERE r.club_member_id=#{memberId} AND c.id = r.club_id;")
    List<Club> queryClubsByMemberId(int memberId);

    @Select("SELECT * FROM  clubs;")
    List<Club> queryAllClub();

    @Select("SELECT COUNT(*) FROM clubs WHERE id = #{clubId};")
    int isClubExistById(int clubId);

    @Select("SELECT COUNT(*) FROM clubs WHERE name = #{name};")
    int isExistByName(String name);

    @Select("DELETE FROM clubs WHERE id=#{clubId}")
    void deleteClub(int clubId);

    //通过id和name同时定位来判断是否会发生update的重复
    @Select("SELECT COUNT(*) FROM clubs WHERE name = #{name} AND id = #{id};")
    int countByDuplicate(@Param("id") int id, @Param("name") String name);
}
