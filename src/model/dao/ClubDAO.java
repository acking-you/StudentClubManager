package model.dao;

import model.MybatisUtil;
import model.entity.Club;
import model.mapper.ClubMapper;
import model.mapper.ClubRelationMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClubDAO {
    static ClubDAO dao = null;
    private final SqlSession sqlSession;
    private final ClubMapper clubMapper;
    private final ClubRelationMapper clubRelationMapper;

    private ClubDAO(boolean autoCommit) {
        sqlSession = MybatisUtil.getSession(autoCommit);
        clubMapper = sqlSession.getMapper(ClubMapper.class);
        clubRelationMapper = sqlSession.getMapper(ClubRelationMapper.class);
    }

    public static ClubDAO getInstance() {
        if (dao == null) {
            dao = new ClubDAO(false);
        }
        return dao;
    }

    public void AddClub(Club club) {
        clubMapper.addClub(club);
        sqlSession.commit();
    }

    public void AddClubList(List<Club> clubList) {
        for (Club club : clubList) {
            clubMapper.addClub(club);
        }
        sqlSession.commit();
    }

    public void UpdateClub(Club club) {
        clubMapper.updateClub(club);
        sqlSession.commit();
    }

    //删除社团，包括对应的关系表
    public void DeleteClub(int clubId) {
        clubRelationMapper.deleteRelationsByClubId(clubId);
        clubMapper.deleteClub(clubId);
        sqlSession.commit();
    }

    //根据名字模糊查询
    public List<Club> QueryClubsByName(String name) {
        return clubMapper.queryClubsByName(name);
    }

    //根据成员id查出所有参加的club
    public List<Club> QueryClubByMemberId(int memberId) {
        return clubMapper.queryClubsByMemberId(memberId);
    }

    public List<Club> QueryAllClub() {
        return clubMapper.queryAllClub();
    }

    public boolean IsClubExist(int id) {
        return clubMapper.isClubExistById(id) > 0;
    }

    public boolean IsClubExist(String name) {
        return clubMapper.isExistByName(name) > 0;
    }

    //根据id和name判断当前数据是否存在，如果存在，则说明是自身重复name未作更改（因为这两个字段都有唯一性
    //如果是不存在，则说明name做了更改，此时才需要进入name判重
    public boolean IsSelfDuplicate(int id, String name) {
        return clubMapper.countByDuplicate(id, name) > 0;
    }
}
