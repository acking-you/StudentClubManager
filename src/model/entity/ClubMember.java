package model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClubMember {
    static int MAX_NAME_LEN = 20;
    static int STUDENT_ID_LEN = 11;
    static int SEX_LEN = 1;
    static int MAX_MAJOR_LEN = 50;
    static int MAX_INTEREST_LEN = 50;
    private int id;
    private String studentId;
    private String name;
    private String sex;
    private int age;
    private String major;
    private String interest;

    public boolean checkStrLen() {
        if (name.length() > MAX_NAME_LEN) {
            return false;
        }
        if (studentId.length() != STUDENT_ID_LEN) {
            return false;
        }
        if (sex.length() != SEX_LEN) {
            return false;
        }
        if (major.length() > MAX_MAJOR_LEN) {
            return false;
        }
        return interest.length() <= MAX_INTEREST_LEN;
    }
}
