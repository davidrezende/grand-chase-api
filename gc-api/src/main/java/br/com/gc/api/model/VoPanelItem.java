package br.com.gc.api.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VoPanelItem {

    private UserItem item;
    private List<UserItemAttribute> attributes;
    private List<UserItemSocket> cards;
    private Integer levelStrength;
}
