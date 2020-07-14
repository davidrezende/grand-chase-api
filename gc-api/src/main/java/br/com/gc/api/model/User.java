package br.com.gc.api.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loginUID;
    @Email(message = "Email invalido")
    private String email; //	varchar(max)
    @NotBlank(message = "O campo login nao pode ser vazio")
    @Size(min = 4, max = 12, message = "Tamanho invalido para o campo login")
    private String login;
    @NotBlank(message = "O campo senha nao pode ser vazio")
    @Size(min = 4, max = 12, message = "Tamanho invalido para o campo senha")
    private String passwd;
    @NotNull(message = "Sexo invalido")
    private Integer sex;
    private Date firstLogin;//smalldatetime
    private Date lastConnect;	//smalldatetime
    private Date lastLogin;	//smalldatetime
    private int playTime;//	int
    private int gamePoint;//	int
    private String ipAddress;	//nvarchar
    private int connecting;	//bit
//    private String modeLevel;	//varbinary
    private int channelingID;	//tinyint
    private int grade; //	int

}