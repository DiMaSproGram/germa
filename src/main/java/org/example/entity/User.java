package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "dbuser")
public class User implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    public int userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_type")
//    @Column(name = "id_type")
    public UserType typeId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_status")
    @Column(name = "id_status")
    public Integer statusId;

    @Column(name = "userName")
    private String username;

    @Column(name = "userPass")
    private String password;

    @Column(name = "enabled")
    public boolean enabled;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(getTypeId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
