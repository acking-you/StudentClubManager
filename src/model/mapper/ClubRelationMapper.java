package model.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface ClubRelationMapper {
    @Insert("INSERT INTO club_member_relations(`club_id`,`club_member_id`) " +
            "VALUES(#{clubId},#{memberId});")
    void addRelations(@Param("clubId")int clubId,@Param("memberId")int memberId);

    @Delete("DELETE FROM club_member_relations WHERE club_member_id = #{memberId} AND club_id=#{clubId};")
    void deleteRelationsByMemberIdAndClubId(@Param("memberId") int memberId,@Param("clubId") int clubId);

    //Áª±íÉ¾³ý
    @Delete("DELETE FROM club_member_relations WHERE club_id = #{clubId};")
    void deleteRelationsByClubId(int clubId);
}
