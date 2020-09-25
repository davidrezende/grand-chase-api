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
@Table(name = "Characters")
@IdClass(CharactersId.class)
public class Character {

    @Id
    @NotNull(message = "Login UID for this user uninformed")
    private Integer loginUID;

    @Id
    private Integer charType;
    private String login;
    private Integer promotion;
    private Long exp;
    private Integer level;
    private Integer win;
    private Integer lose;
    private Long exps4;
    private Long nExp;
    private Integer nWin;
    private Integer nLose;
}
