package br.com.gc.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@IdClass(UserItemAttributeId.class)
@Table(name = "UIGAUserItemAttribute")
public class UserItemAttribute {
    /*  insert into gc.dbo.UIGAUserItemAttribute (LoginUID, ItemUID, SlotID, TypeID, Value, AttributeState)
      VALUES (@loginid1, @itemuid1, '0', '-1', '0', '0')*/

    @Id
    private Integer loginUID;
    @Id
    private Long itemUID;
    @Id
    private int slotID;
    @NotNull(message = "Type ID uninformed")
    private int typeID;
    @NotNull(message = "Value uninformed")
    private int value;
    @NotNull(message = "Attribute state uninformed")
    private int attributeState;
}
