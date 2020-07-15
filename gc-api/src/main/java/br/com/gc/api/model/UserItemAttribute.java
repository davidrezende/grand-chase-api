package br.com.gc.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "UIGAUserItemAttribute")
public class UserItemAttribute implements Serializable {
    /*  insert into gc.dbo.UIGAUserItemAttribute (LoginUID, ItemUID, SlotID, TypeID, Value, AttributeState)
      VALUES (@loginid1, @itemuid1, '0', '-1', '0', '0')*/


    @NotNull(message = "Login ID for this user uninformed")
    @Id
    private Integer loginUID;
    @NotNull(message = "Item ID uninformed")
    @Id
    private Integer itemUID;
    @NotNull(message = "Slot ID uninformed")
    @Id
    private Integer slotID;
    @NotNull(message = "Type ID uninformed")
    private String typeID;
    @NotNull(message = "Value uninformed")
    private Integer value;
    @NotNull(message = "Attribute state uninformed")
    private Integer attributeState;
}
