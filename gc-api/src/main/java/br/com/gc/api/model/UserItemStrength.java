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
@IdClass(UserItemStrengthId.class)
@Table(name = "UIGAUserItemStrength")
public class UserItemStrength {

/*
    insert into gc.dbo.UIGAUserItemStrength (LoginUID, ItemUID, StrengthOrder, StrengthLevel, EquippedItemUID)
    values (@loginid1, @Stone, '1', '17', @itemuid1)*/

    @NotNull
    @Id
    private Integer loginUID;
    @NotNull
    @Id
    private Long itemUID; //stone
    @NotNull
    @Id
    private Integer strengthOrder;
    @NotNull
    private Integer strengthLevel;
    @NotNull
    private Long equippedItemUID; //itemUID


}
