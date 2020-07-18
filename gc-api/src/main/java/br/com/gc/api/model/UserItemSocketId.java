package br.com.gc.api.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserItemSocketId implements Serializable {
    private Integer loginUID;
    private Long itemUID;
    private Integer slotID;
}
