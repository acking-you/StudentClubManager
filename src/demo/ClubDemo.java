package demo;

import model.entity.Club;
import service.ClubService;
import view.AddClubInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClubDemo {
    static List<Club> makeDemo(){
        //添加100个社团
        int cateSum = AddClubInfo.categoryOptions.length;
        List<Club> clubList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Club club = new Club();
            club.setCategory(AddClubInfo.categoryOptions[i%cateSum]);
            club.setName(String.format("测试%d",i));
            club.setMemberCount(0);
            clubList.add(club);
        }
        return clubList;
    }
    static void pushClubDemo(){
        List<Club> clubList = makeDemo();
        ClubService.AddClubList(clubList);
    }

    public static void main(String[] args) {
        pushClubDemo();
    }
}
