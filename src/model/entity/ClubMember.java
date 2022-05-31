package model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClubMember {
    private int memberCount;
    private String category;
    private String Name;
}
