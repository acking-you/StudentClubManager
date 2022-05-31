package model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Club {
    private String studentId;
    private String name;
    private String sex;
    private int age;
    private String major;
    private String interest;
}
