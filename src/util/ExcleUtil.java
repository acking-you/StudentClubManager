package util;

import model.MybatisUtil;
import model.entity.Club;
import model.entity.ClubMember;
import model.entity.Student;
import model.mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import service.ClubMemberService;
import service.ClubService;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcleUtil {
    static public void ExportClubToExcle(String filepath) throws IOException {
        List<Club> clubs = ClubService.QueryAllClub();
        if(clubs==null||clubs.isEmpty()){
            throw new IOException("����Ϊ��");
        }
        //����workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("������Ϣ");
        HSSFRow row = sheet.createRow(0);
        String[] s = {"����id", "��������", "���ŷ���", "����������"};
        for (int i = 0; i < s.length; i++) {
            row.createCell(i).setCellValue(s[i]);
        }

        //�����ݴ���workbook
        for (int j = 0; j < clubs.size(); j++) {
            HSSFRow r = sheet.createRow(j + 1);
            Club club = clubs.get(j);
            r.createCell(0).setCellValue(club.getId());
            r.createCell(1).setCellValue(club.getName());
            r.createCell(2).setCellValue(club.getCategory());
            r.createCell(3).setCellValue(club.getMemberCount());
        }

        //��ʼд����̳־û�
        OutputStream out = new FileOutputStream(filepath);
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println("����club�ɹ���");
        MessageUtil.Info("����club�ɹ���");
    }

    static public void ImportClubInfoByExcle(String fileName) throws IOException {
        List<Club> clubList = new ArrayList<>();

        //��ȡexcle��ȡ����
        InputStream inputStream = new FileInputStream(fileName);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        int size = workbook.getNumberOfSheets();
        for (int i = 0; i < size; i++) {
            HSSFSheet hssfSheet = workbook.getSheetAt(i);
            if (hssfSheet == null)
                continue;
            for (int j = 1; j <= hssfSheet.getLastRowNum(); j++) {
                HSSFRow row = hssfSheet.getRow(j);
                int firstCellNum = row.getFirstCellNum();
                int lastCellNum = row.getLastCellNum();
                Club club = new Club();
                int cnt = 1;
                for (int k = firstCellNum; k < lastCellNum; k++) {
                    HSSFCell cell = row.getCell(k);
                    if (cell == null) {
                        continue;
                    }
                    if (cnt == 1) {
                        club.setId((int)cell.getNumericCellValue());
                    } else if (cnt == 2) {
                        club.setName(cell.getStringCellValue());
                    } else if (cnt == 3) {
                        club.setCategory(cell.getStringCellValue());
                    } else if (cnt == 4) {
                        club.setMemberCount((int)cell.getNumericCellValue());
                    }
                    cnt++;
                }
                clubList.add(club);
            }
        }
        inputStream.close();
        workbook.close();

        //�����ݵ������ݿ�
        ClubService.AddClubList(clubList);

        System.out.println("club�������ݿ�ɹ���");
        MessageUtil.Info("club�������ݿ�ɹ���");
    }


    static public void ExportClubMemberToExcle(int clubId,String filepath) throws IOException {
        List<ClubMember> clubMemberList = ClubMemberService.QueryClubMembersByClubId(clubId);
        if(clubMemberList==null||clubMemberList.isEmpty()){
            throw new IOException("����Ϊ��");
        }
        //����workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("���ų�Ա��Ϣ");
        HSSFRow row = sheet.createRow(0);
        String[] s = {"ѧ��", "����", "�Ա�", "����","רҵ","��Ȥ����"};
        for (int i = 0; i < s.length; i++) {
            row.createCell(i).setCellValue(s[i]);
        }

        //�����ݴ���workbook
        for (int j = 0; j < clubMemberList.size(); j++) {
            HSSFRow r = sheet.createRow(j + 1);
            ClubMember clubMember = clubMemberList.get(j);
            r.createCell(0).setCellValue(clubMember.getStudentId());
            r.createCell(1).setCellValue(clubMember.getName());
            r.createCell(2).setCellValue(clubMember.getSex());
            r.createCell(3).setCellValue(clubMember.getAge());
            r.createCell(4).setCellValue(clubMember.getMajor());
            r.createCell(5).setCellValue(clubMember.getInterest());
        }

        //��ʼд����̳־û�
        OutputStream out = new FileOutputStream(filepath);
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println("����club�ɹ���");
        MessageUtil.Info("����club�ɹ���");
    }


    static public void ImportClubMemberInfoByExcle(int clubId,String fileName) throws IOException {
        List<ClubMember> clubMembers = new ArrayList<>();

        //��ȡexcle��ȡ����
        InputStream inputStream = new FileInputStream(fileName);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        int size = workbook.getNumberOfSheets();
        for (int i = 0; i < size; i++) {
            HSSFSheet hssfSheet = workbook.getSheetAt(i);
            if (hssfSheet == null)
                continue;
            for (int j = 1; j <= hssfSheet.getLastRowNum(); j++) {
                HSSFRow row = hssfSheet.getRow(j);
                int firstCellNum = row.getFirstCellNum();
                int lastCellNum = row.getLastCellNum();
                ClubMember clubMember = new ClubMember();
                int cnt = 1;
                for (int k = firstCellNum; k < lastCellNum; k++) {
                    HSSFCell cell = row.getCell(k);
                    if (cell == null) {
                        continue;
                    }
                    if (cnt == 1) {
                        clubMember.setStudentId(cell.getStringCellValue());
                    } else if (cnt == 2) {
                        clubMember.setName(cell.getStringCellValue());
                    } else if (cnt == 3) {
                        clubMember.setSex(cell.getStringCellValue());
                    } else if (cnt == 4) {
                        clubMember.setAge((int)cell.getNumericCellValue());
                    }else if(cnt==5){
                        clubMember.setMajor(cell.getStringCellValue());
                    }else if(cnt==6){
                        clubMember.setInterest(cell.getStringCellValue());
                    }
                    cnt++;
                }
                clubMembers.add(clubMember);
            }
        }
        inputStream.close();
        workbook.close();

        //�����ݵ������ݿ�
        ClubMemberService.AddClubMemberList(clubId,clubMembers);
        System.out.println("club�������ݿ�ɹ���");
        MessageUtil.Info("club�������ݿ�ɹ���");
    }
}

