package model.dao;

import model.MybatisUtil;
import model.entity.ClubMember;
import model.mapper.ClubMapper;
import model.mapper.ClubMemberMapper;
import model.mapper.ClubRelationMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClubMemberDAO {
    static ClubMemberDAO dao = null;
    private final SqlSession sqlSession;
    private final ClubMemberMapper clubMemberMapper;
    private final ClubRelationMapper clubRelationMapper;
    private final ClubMapper clubMapper;

    private ClubMemberDAO(boolean autoCommit) {
        sqlSession = MybatisUtil.getSession(autoCommit);
        clubMemberMapper = sqlSession.getMapper(ClubMemberMapper.class);
        clubRelationMapper = sqlSession.getMapper(ClubRelationMapper.class);
        clubMapper = sqlSession.getMapper(ClubMapper.class);
    }

    public static ClubMemberDAO getInstance() {
        if (dao == null) {
            dao = new ClubMemberDAO(false);
        }
        return dao;
    }

    public void AddClubMember(int clubId, ClubMember clubMember) {
        //添加成员
        clubMemberMapper.addClubMember(clubMember);
        //得到该成员的id
        int id = clubMemberMapper.queryIdFromStudentId(clubMember.getStudentId());
        //添加多对多关系
        clubRelationMapper.addRelations(clubId, id);
        //更新社团的count
        clubMapper.plusMemberCountById(clubId, 1);
        sqlSession.commit();
    }

    public void AddClubMemberList(int clubId, List<ClubMember> clubMemberList) {
        for (ClubMember clubMember : clubMemberList) {
            //添加成员
            clubMemberMapper.addClubMember(clubMember);
            //得到该成员的id
            int id = clubMemberMapper.queryIdFromStudentId(clubMember.getStudentId());
            //添加多对多关系
            clubRelationMapper.addRelations(clubId, id);
        }
        //更新社团的count
        clubMapper.plusMemberCountById(clubId, clubMemberList.size());
        sqlSession.commit();
    }

    public void UpdateClubMember(ClubMember clubMember) {
        clubMemberMapper.updateClubMember(clubMember);
        sqlSession.commit();
    }

    //根据社团所属和名字进行模糊查询
    public List<ClubMember> QueryClubMemberByName(int clubId, String name) {
        return clubMemberMapper.queryClubMemberByName(clubId, name);
    }

    //查询社团的全部成员
    public List<ClubMember> QueryClubMemberByClubId(int clubId) {
        return clubMemberMapper.queryClubMemberByClubId(clubId);
    }

    //删除社团成员的同时，删除对应的社团关系
    public void DeleteClubMemberById(int clubId, int memberId) {
        clubRelationMapper.deleteRelationsByMemberIdAndClubId(memberId, clubId);
        clubMapper.minusMemberCountById(clubId);
        clubMemberMapper.deleteClubMemberById(memberId);
        sqlSession.commit();
    }

    public boolean IsClubMemberExist(int id) {
        return clubMemberMapper.isClubMemBerExistById(id) > 0;
    }
}
