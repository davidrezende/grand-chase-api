package br.com.gc.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name= "UIGAUserItemSocket")
public class UserItemSocket implements Serializable {
   /* insert into gc.dbo.UIGAUserItemSocket (LoginUID, ItemUID, SlotID, CardID, SocketState)
    values (@loginid1, @itemuid1, '0', '1668770', '3')*/
   @Id
   private Integer loginUID;
   @Id
   private Integer itemUID;
   @Id
   private Integer slotID;
   private Integer cardID;
   private Integer socketState;

}
