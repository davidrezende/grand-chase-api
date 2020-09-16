package br.com.gc.api.security;

import br.com.gc.api.model.GCApiUsers;
import br.com.gc.api.model.User;
import br.com.gc.api.model.VoUserSystem;
import br.com.gc.api.repository.GCApiUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private GCApiUsersRepository gcApiUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<GCApiUsers> userOptional = gcApiUsersRepository.findByLogin(email);
        GCApiUsers user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario e/ou senha incorretos"));
        return new VoUserSystem(user, getPermissions(user));
    }

    private Collection<? extends GrantedAuthority> getPermissions(GCApiUsers user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getPermissions()));
//        user.getUserPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
        return authorities;
    }
}
