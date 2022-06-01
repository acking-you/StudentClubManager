package service;

import model.dao.ClubMemberDAO;
import model.entity.ClubMember;

import java.util.List;
import java.util.Objects;

public class ClubMemberService {
    public static void AddClubMember(int clubId, ClubMember member)throws RuntimeException{
        if(!ClubService.isClubExist(clubId)){
            throw new RuntimeException("社团不存在");
        }
        if (checkClubMember(member) != 0) {
            throw new RuntimeException("成员信息不合法");
        }
        ClubMemberDAO.getInstance().AddClubMember(clubId,member);
    }

    //把一堆人加入到一个社团中
    public static void AddClubMemberList(int clubId,List<ClubMember> clubMemberList)throws RuntimeException{
        if(!ClubService.isClubExist(clubId)){
            throw new RuntimeException("社团不存在");
        }
        for(ClubMember member:clubMemberList){
            if (checkClubMember(member) != 0) {
                throw new RuntimeException("成员信息不合法");
            }
        }

        ClubMemberDAO.getInstance().AddClubMemberList(clubId,clubMemberList);
    }
    public static void UpdateClubMember(ClubMember member)throws RuntimeException{
        if(checkClubMember(member)!=0){
            throw new RuntimeException("成员信息不合法");
        }

        ClubMemberDAO.getInstance().UpdateClubMember(member);
    }

    public static List<ClubMember> QueryClubMemberByName(int clubId,String name)throws RuntimeException{
        if(ClubService.checkName(name)!=0){
            throw new RuntimeException("名字不合法");
        }
        if (!ClubService.isClubExist(clubId)) {
            throw  new RuntimeException("社团不存在");
        }
        return ClubMemberDAO.getInstance().QueryClubMemberByName(clubId,name);
    }

    public static void DeleteClubMemberById(int clubId,int id)throws RuntimeException{
        if (!ClubService.isClubExist(clubId)) {
            throw new RuntimeException("俱乐部不存在");
        }
        if(isClubMemberExist(id)){
            throw new RuntimeException("俱乐部成员不存在");
        }

        ClubMemberDAO.getInstance().DeleteClubMemberById(clubId,id);
    }

    public static List<ClubMember> QueryClubMembersByClubId(int clubId)throws RuntimeException{
        if (!ClubService.isClubExist(clubId)){
            throw new RuntimeException("俱乐部不存在");
        }

        return ClubMemberDAO.getInstance().QueryClubMemberByClubId(clubId);
    }

    //start to check
    private static int checkClubMember(ClubMember member){
        //TODO 检查clubMember
        if (!member.checkStrLen()){
            return -1;
        }
        if(ClubService.checkName(member.getName())!=0){
            return -1;
        }
        if(!(Objects.equals(member.getSex(), "男") || Objects.equals(member.getSex(), "女"))){
            return -1;
        }
        return 0;
    }

    static boolean isClubMemberExist(int id){
        //TODO 判断是否存在
        return !ClubMemberDAO.getInstance().IsClubMemberExist(id);
    }
}
