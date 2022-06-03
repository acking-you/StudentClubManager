package demo;

import model.entity.ClubMember;
import service.ClubMemberService;
import view.AddMemberInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClubMemberDemo {
    static class ClubMemberDemoData{
        public ClubMember clubMember;
        public int clubId;
        ClubMemberDemoData(){
            clubMember = new ClubMember();
        }
    }
    public static List<ClubMemberDemoData> makeDemo(){
        List<ClubMemberDemoData> list = new ArrayList<>();
        int len = AddMemberInfo.majors.length;
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int id = random.nextInt(99)+54;
            int age = random.nextInt(100 )+1;
            int index = random.nextInt(len);
            ClubMemberDemoData clubMemberDemoData = new ClubMemberDemoData();
            clubMemberDemoData.clubId = id;
            clubMemberDemoData.clubMember.setStudentId(String.format("B202004%04d",i));
            clubMemberDemoData.clubMember.setName(String.format("²âÊÔÈËÔ±%d",i));
            clubMemberDemoData.clubMember.setSex(i%2==0?"ÄÐ":"Å®");
            clubMemberDemoData.clubMember.setAge(age);
            clubMemberDemoData.clubMember.setMajor(AddMemberInfo.majors[index]);
            clubMemberDemoData.clubMember.setInterest("coding");
            list.add(clubMemberDemoData);
        }
        return list;
    }
    public static void pushDemo(){
        List<ClubMemberDemoData>list = makeDemo();
        for(ClubMemberDemoData t:list){
            ClubMemberService.AddClubMember(t.clubId,t.clubMember);
        }
    }

    public static void main(String[] args) {
        pushDemo();
    }
}
