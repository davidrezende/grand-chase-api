package br.com.gc.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//VCGAVirtualCash
@Getter
@Setter
@ToString
@Entity
@Table(name = "CIGACharacterInfo")
public class CharacterInfo {

    @Id
    @NotNull(message = "Login UID for this user uninformed")
    private Integer loginUID;
    private Integer charType;
    private Integer slotNo;
    private Integer gamePoint;
}
