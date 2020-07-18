package br.com.gc.api.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name= "UIGAUserItemSocket")
@IdClass(UserItemSocketId.class)
public class UserItemSocket {
   /* insert into gc.dbo.UIGAUserItemSocket (LoginUID, ItemUID, SlotID, CardID, SocketState)
    values (@loginid1, @itemuid1, '0', '1668770', '3')*/
   @Id
   private Integer loginUID;
   @Id
   private Long itemUID;
   @Id
   private int slotID;
   private int cardID;
   private int socketState;
}
