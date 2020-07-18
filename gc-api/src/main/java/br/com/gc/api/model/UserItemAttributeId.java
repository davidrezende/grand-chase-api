package br.com.gc.api.model;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserItemAttributeId implements Serializable {
    private Integer loginUID;
    private Long itemUID;
    private Integer slotID;
}
