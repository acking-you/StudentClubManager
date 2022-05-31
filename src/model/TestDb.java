package model;

import model.dao.ClubDAO;
import model.dao.ClubMemberDAO;
import model.entity.Club;
import model.entity.ClubMember;

import javax.swing.*;
import java.util.List;

public class TestDb {

    public static void main(String[] args) {
//        Club club = new Club().setName("测试").setCategory("学习类").setMemberCount(100).setId(2);
//        ClubDAO.getInstance().UpdateClub(club);
        ClubMember m = new ClubMember().
                setName("你好").setAge(10).
                setMajor("我的专业").setInterest("我的兴趣").
                setSex("女").setStudentId("B31").setId(1);
        System.out.println(ClubMemberDAO.getInstance().IsClubMemberExist(2));
    }

}
