package service;

import model.dao.ClubDAO;
import model.entity.Club;
import util.CharacterUtil;

import java.util.Comparator;
import java.util.List;

public class ClubService {
    public static void AddClub(Club club) throws RuntimeException {
        if (checkClub(club) != 0) {
            throw new RuntimeException("club参数检查出错");
        }
        if (ClubDAO.getInstance().IsClubExist(club.getName())) {
            throw new RuntimeException("社团名字已经存在");
        }

        ClubDAO.getInstance().AddClub(club);
    }

    public static void AddClubList(List<Club> clubList) throws RuntimeException {
        if (clubList == null || clubList.isEmpty()) {
            throw new RuntimeException("clubList数据为空");
        }
        ClubDAO.getInstance().AddClubList(clubList);
    }

    public static void UpdateClub(Club club) throws RuntimeException {
        if (checkClub(club) != 0) {
            throw new RuntimeException("club参数检查出错");
        }
        if (isClubExist(club.getId())) {
            throw new RuntimeException("club不存在");
        }
        //不是自身名称重复的情况下，更新时会名称重复，则返回错误
        if (!ClubDAO.getInstance().IsSelfDuplicate(club.getId(), club.getName()) && ClubDAO.getInstance().IsClubExist(club.getName())) {
            throw new RuntimeException("club名称重复");
        }
        ClubDAO.getInstance().UpdateClub(club);
    }

    public static void DeleteClub(Club club) throws RuntimeException {
        if (checkClub(club) != 0) {
            throw new RuntimeException("club参数检查出错");
        }
        if (isClubExist(club.getId())) {
            throw new RuntimeException("club不存在");
        }
        ClubDAO.getInstance().DeleteClub(club.getId());
    }

    public static List<Club> QueryClubsByName(String name) throws RuntimeException {
        if (checkName(name) != 0) {
            throw new RuntimeException("名字不符合条件");
        }
        return ClubDAO.getInstance().QueryClubsByName(name);
    }

    public static List<Club> QueryClubByMemberId(int memberId) throws RuntimeException {
        if (ClubMemberService.isClubMemberExist(memberId)) {
            throw new RuntimeException("memberId不存在");
        }
        return ClubDAO.getInstance().QueryClubByMemberId(memberId);
    }

    public static List<Club> QueryAllClub() {
        return ClubDAO.getInstance().QueryAllClub();
    }

    //根据order值返回按memberCount排序的结果：true顺序，false逆序
    public static List<Club> QueryAllClubByOrder(boolean order) {
        List<Club> list = QueryAllClub();
        if (order) {
            list.sort(Comparator.comparingInt(Club::getMemberCount));
        } else {
            list.sort((a, b) -> b.getMemberCount() - a.getMemberCount());
        }
        return list;
    }

    //start to check
    public static int checkName(String name) {
        //TODO 检查名，只能含有汉字或者英文字符
        if (!CharacterUtil.isValidNameString(name)) {
            return -1;
        }
        return 0;
    }

    private static int checkClub(Club club) {
        //TODO 合法返回0，其他值不合法
        if (!club.checkStrLen()) {
            return -1;
        }
        if (checkName(club.getName()) != 0) {
            return -1;
        }
        return 0;
    }

    static boolean isClubExist(int id) {
        //TODO 根据id判断这个club是否存在
        return !ClubDAO.getInstance().IsClubExist(id);
    }
}
