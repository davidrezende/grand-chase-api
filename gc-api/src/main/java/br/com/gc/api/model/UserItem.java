package br.com.gc.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UIGAUserItem")
public class UserItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemUID;
    @NotNull(message = "Login UID for this user uninformed")
    private Integer loginUID;
    @NotNull(message = "Item UID for this user uninformed")
    private Integer itemID;
    @NotNull(message = "Item type for this user uninformed")
    private Integer gradeID;
    private String delDateA;
    private Integer delState = 0;
    private String WIGAUID = "-1";
}
