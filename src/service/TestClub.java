package service;

import model.entity.Club;
import org.junit.Test;

import java.util.List;

public class TestClub {
    @Test
    public void TestAddClub() {
        Club c = new Club().setName("²âÊÔb").setCategory("¹þ¹þ¹þ").setMemberCount(32);
        try {
            ClubService.AddClub(c);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestUpdateClub() {
        List<Club> l = ClubService.QueryClubsByName("²âÊÔ");
        l.get(0).setCategory("wocao");
        ClubService.UpdateClub(l.get(0));
    }

    @Test
    public void TestQueryClubByMemberId() {
        List<Club> l = ClubService.QueryClubByMemberId(1);
    }

    @Test
    public void TestQueryAllClub() {
        List<Club> list = ClubService.QueryAllClub();
        for (Club i : list) {
            System.out.println(i);
        }
    }
}
