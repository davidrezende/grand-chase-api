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
@IdClass(CharacterInfoId.class)
@Table(name = "CIGACharacterInfo")
public class CharacterInfo {

    @Id
    @NotNull(message = "Login UID for this user uninformed")
    private Integer loginUID;
    @Id
    private Integer charType;
    private Integer slotNo;
    private Integer gamePoint;
}
