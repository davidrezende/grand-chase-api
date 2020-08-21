package br.com.gc.api.model;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserItemDurationId implements Serializable {
    private Integer loginUID;
    private Long itemUID;
}
