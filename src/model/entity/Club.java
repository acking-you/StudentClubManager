package model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Club {
    private int id;
    private int memberCount;
    private String category;
    private String name;

    static int MAX_CATEGORY_LEN = 30;
    static int MAX_NAME_LEN = 20;

    public boolean checkStrLen(){
        if (category.length()>MAX_CATEGORY_LEN){
            return false;
        }
        return name.length() <= MAX_NAME_LEN;
    }
}
