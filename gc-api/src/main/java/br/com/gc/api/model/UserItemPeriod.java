package br.com.gc.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Entity
@IdClass(UserItemDurationId.class)
@Table(name = "UIGAUserItemPeriod")
public class UserItemPeriod {
    /*    insert into UIGAUserItem  (LoginUID, ItemID, GradeID, DelDateA, DelState, WIGAUID)
        values(@loginid1,@itemid1,'3',getdate(),'0','-1')*/
    @Id
    @NotNull(message = "Item UID for this user uninformed")
    private Long itemUID;

    @Id
    @NotNull(message = "Login UID for this user uninformed")
    private Integer loginUID;

    @NotNull(message = "Period of item uninformed")
    private Integer period;

    @NotNull(message = "End date of item uninformed")
    private String endDate;//smalldatetime

}
