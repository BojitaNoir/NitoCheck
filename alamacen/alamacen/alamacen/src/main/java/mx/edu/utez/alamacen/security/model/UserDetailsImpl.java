package mx.edu.utez.alamacen.security.model;

import mx.edu.utez.alamacen.model.user.UserBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {


    private String username;
    private String password;

    private  boolean blocked;
    private boolean enable;

    private Collection<? extends GrantedAuthority>
    authorities;

    public UserDetailsImpl(String username, String password, boolean blocked, boolean enable, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.blocked = blocked;
        this.enable = enable;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserBean user){
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRol()))
                .collect(Collectors.toSet());
        return new UserDetailsImpl(user.getUser(),user.getPassword(), user.getBlocked(),
        user.getStatus(), authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return blocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
