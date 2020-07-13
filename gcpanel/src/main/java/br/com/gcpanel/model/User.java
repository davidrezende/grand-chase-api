package br.com.gcpanel.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String login;
    private String passwd;
    private Integer sex;
    private Integer loginUID;
  /*  private String firstLogin;//smalldatetime
    private Date lastConnect;	//smalldatetime
    private Date lastLogin;	//smalldatetime*/
//    private Integer playTime;//	int
//    @Column(name= "gamePoint")
    private int gamePoint;//	int
//    private String ipAddress;	//nvarchar
    private Integer connecting;	//bit
//    private String modeLevel;	//varbinary
    private Integer channelingID;	//tinyint

    private Integer grade; //	int
    private String email; //	varchar(max)
}