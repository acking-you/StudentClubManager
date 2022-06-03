package util;

import org.junit.Test;

import java.io.IOException;

public class TestExcleUtil {
    @Test
    public void TestExportClubToExcle() throws IOException {
        ExcleUtil.ExportClubToExcle("C:\\Users\\Alone\\Desktop\\StuManer-Practice-master\\test.xlsx");
    }

    @Test
    public void TestImportClubByExcle() throws IOException {
        ExcleUtil.ImportClubInfoByExcle("C:\\Users\\Alone\\Desktop\\StuManer-Practice-master\\test.xls");
    }

    @Test
    public void TestExportClubMemberToExcle() throws IOException {
        ExcleUtil.ExportClubMemberToExcle(39, "C:\\Users\\Alone\\Desktop\\StuManer-Practice-master\\test1.xls");
    }

    @Test
    public void TestImportClubMemberByExcle() throws IOException {
        ExcleUtil.ImportClubMemberInfoByExcle(39, "C:\\Users\\Alone\\Desktop\\StuManer-Practice-master\\test.xls");
    }
}
