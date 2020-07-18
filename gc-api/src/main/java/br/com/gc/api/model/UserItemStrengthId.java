package br.com.gc.api.model;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserItemStrengthId implements Serializable {
    private Integer loginUID;
    private Long itemUID;
    private Integer strengthOrder;
}
