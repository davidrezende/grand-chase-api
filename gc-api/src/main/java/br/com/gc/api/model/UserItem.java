package br.com.gc.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "UIGAUserItem")
public class UserItem {
    /*    insert into UIGAUserItem  (LoginUID, ItemID, GradeID, DelDateA, DelState, WIGAUID)
        values(@loginid1,@itemid1,'3',getdate(),'0','-1')*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemUID;
    @NotNull(message = "Login ID for this user uninformed")
    private Integer loginUID;
    @NotNull(message = "Item ID for this user uninformed")
    private Integer itemID;
    @NotNull(message = "Item type for this user uninformed")
    private Integer gradeID;
    private Date delDateA;
    private Integer delState = 0;
    private String WIGAUID = "-1";
}
