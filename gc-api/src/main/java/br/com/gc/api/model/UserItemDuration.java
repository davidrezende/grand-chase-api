package br.com.gc.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Entity
@IdClass(UserItemDurationId.class)
@Table(name = "UIGAUserItemDuration")
public class UserItemDuration{
    /*    insert into UIGAUserItem  (LoginUID, ItemID, GradeID, DelDateA, DelState, WIGAUID)
        values(@loginid1,@itemid1,'3',getdate(),'0','-1')*/
    @Id
    @NotNull(message = "Item UID for this user uninformed")
    private Long itemUID;

    @Id
    @NotNull(message = "Login UID for this user uninformed")
    private Integer loginUID;

    @NotNull(message = "Amount stack item uninformed")
    private Integer duration;
}
