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
@Table(name = "UIGAUserItemStrength")
public class UserItemStrength implements Serializable {

/*
    insert into gc.dbo.UIGAUserItemStrength (LoginUID, ItemUID, StrengthOrder, StrengthLevel, EquippedItemUID)
    values (@loginid1, @Stone, '1', '17', @itemuid1)*/

    @NotNull
    @Id
    private Integer loginUID;
    @NotNull
    @Id
    private Integer itemUID;
    @NotNull
    private Integer strengthOrder;
    @NotNull
    private Integer strenghtLevel;
    @NotNull
    private Integer equippedItemUID;


}
