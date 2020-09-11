package br.com.gc.api.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class VoUserSystem extends User {

    private static final long serialVersionUID = 1L;

    private GCApiUsers user;

    public VoUserSystem(br.com.gc.api.model.GCApiUsers user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getLogin(), user.getPassword(), authorities);
        this.user = user;
    }

}
