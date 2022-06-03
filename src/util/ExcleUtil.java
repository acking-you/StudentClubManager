package util;

import model.dao.ClubDAO;
import model.entity.Club;
import model.entity.ClubMember;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import service.ClubMemberService;
import service.ClubService;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcleUtil {
    static public void ExportClubToExcle(String filepath) throws IOException {
        List<Club> clubs = ClubService.QueryAllClub();
        if (clubs == null || clubs.isEmpty()) {
            throw new IOException("数据为空");
        }
        //创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("社团信息");
        HSSFRow row = sheet.createRow(0);
        String[] s = {"社团id", "社团名称", "社团分类", "社团总人数"};
        for (int i = 0; i < s.length; i++) {
            row.createCell(i).setCellValue(s[i]);
        }

        //将数据存入workbook
        for (int j = 0; j < clubs.size(); j++) {
            HSSFRow r = sheet.createRow(j + 1);
            Club club = clubs.get(j);
            r.createCell(0).setCellValue(club.getId());
            r.createCell(1).setCellValue(club.getName());
            r.createCell(2).setCellValue(club.getCategory());
            r.createCell(3).setCellValue(club.getMemberCount());
        }

        //开始写入磁盘持久化
        OutputStream out = new FileOutputStream(filepath);
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println("导出club成功！");
        MessageUtil.Info("导出club成功！");
    }

    static public void ImportClubInfoByExcle(String fileName) throws IOException {
        List<Club> clubList = new ArrayList<>();

        //读取excle获取数据
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
                        club.setId((int) cell.getNumericCellValue());
                    } else if (cnt == 2) {
                        club.setName(cell.getStringCellValue());
                    } else if (cnt == 3) {
                        club.setCategory(cell.getStringCellValue());
                    } else if (cnt == 4) {
                        club.setMemberCount((int) cell.getNumericCellValue());
                    }
                    cnt++;
                }
                clubList.add(club);
            }
        }
        inputStream.close();
        workbook.close();

        //将数据导入数据库
        ClubService.AddClubList(clubList);

        System.out.println("club导入数据库成功！");
        MessageUtil.Info("club导入数据库成功！");
    }


    static public void ExportClubMemberToExcle(int clubId, String filepath) throws IOException {
        List<ClubMember> clubMemberList = ClubMemberService.QueryClubMembersByClubId(clubId);
        if (clubMemberList == null || clubMemberList.isEmpty()) {
            throw new IOException("数据为空");
        }
        //创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("社团成员信息");
        HSSFRow row = sheet.createRow(0);
        String[] s = {"学号", "姓名", "性别", "年龄", "专业", "兴趣爱好"};
        for (int i = 0; i < s.length; i++) {
            row.createCell(i).setCellValue(s[i]);
        }

        //将数据存入workbook
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

        //开始写入磁盘持久化
        OutputStream out = new FileOutputStream(filepath);
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println("导出club成功！");
        MessageUtil.Info("导出club成功！");
    }


    static public void ImportClubMemberInfoByExcle(int clubId, String fileName) throws IOException {
        List<ClubMember> clubMembers = new ArrayList<>();
        if (!ClubDAO.getInstance().IsClubExist(clubId)) {
            throw new RuntimeException(String.format("社团%d不存在", clubId));
        }
        //读取excle获取数据
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
                        clubMember.setAge((int) cell.getNumericCellValue());
                    } else if (cnt == 5) {
                        clubMember.setMajor(cell.getStringCellValue());
                    } else if (cnt == 6) {
                        clubMember.setInterest(cell.getStringCellValue());
                    }
                    cnt++;
                }
                clubMembers.add(clubMember);
            }
        }
        inputStream.close();
        workbook.close();

        //将数据导入数据库
        ClubMemberService.AddClubMemberList(clubId, clubMembers);
        System.out.println("club导入数据库成功！");
        MessageUtil.Info("club导入数据库成功！");
    }

    public static void ExportClubToExcleUI() throws IOException {
        //设置Windows风格
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择导出路径");
        //设置过滤器进行提示
        FileNameExtensionFilter filter = new FileNameExtensionFilter("(*.xls)", "xls");
        fileChooser.setFileFilter(filter);
        int option = fileChooser.showSaveDialog(null);
        //如果选择了保存，则进行Excel的导出
        if (option == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            String absPath = f.getAbsolutePath();
            if (!absPath.endsWith(".xls")) {
                absPath += ".xls";
            }
            ExportClubToExcle(absPath);
        }
    }

    //导出某个社团的全部成员信息
    public static void ExportClubMemberToExcleUI(int clubId) throws IOException {
        //设置Windows风格
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择导出路径");
        //设置过滤器进行提示
        FileNameExtensionFilter filter = new FileNameExtensionFilter("(*.xls)", "xls");
        fileChooser.setFileFilter(filter);
        int option = fileChooser.showSaveDialog(null);
        //如果选择了保存，则进行Excel的导出
        if (option == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            String absPath = f.getAbsolutePath();
            if (!absPath.endsWith(".xls")) {
                absPath += ".xls";
            }
            ExportClubMemberToExcle(clubId, absPath);
        }
    }

    public static void ImportClubInfoByExcleUI() throws IOException {
        //设置Windows风格
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择导入文件");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //设置过滤器只显示xls文件
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".xls");
            }

            @Override
            public String getDescription() {
                return "Excle(*.xls)";
            }
        });
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            String absPath = fileChooser.getSelectedFile().getAbsolutePath();
            //开始执行导入操作
            ImportClubInfoByExcle(absPath);
        }
    }

    //导入成员到某个社团
    public static void ImportClubMemberInfoByExcleUI(int clubId) throws IOException {
        //设置Windows风格
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择导入文件");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //设置过滤器只显示xls文件
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".xls");
            }

            @Override
            public String getDescription() {
                return "Excle(*.xls)";
            }
        });
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            String absPath = fileChooser.getSelectedFile().getAbsolutePath();
            //开始执行导入操作
            ImportClubMemberInfoByExcle(clubId, absPath);
        }
    }

    public static void main(String[] args) throws IOException {
        ImportClubMemberInfoByExcleUI(49);
    }
}


