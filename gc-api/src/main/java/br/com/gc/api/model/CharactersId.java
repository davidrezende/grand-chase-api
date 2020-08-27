package br.com.gc.api.model;

import lombok.*;
import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CharactersId implements Serializable {
    private Integer loginUID;
    private Integer charType;
}
