package com.example.platformerp.model;

import com.example.platformerp.enums.Permissions;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends BaseEntity implements UserDetails {

    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<UserRole> roles   = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private GroupEntity group;
    private Boolean isActive;
    private Boolean isVerify;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> auth = new ArrayList<>();
        for (UserRole role : roles) {
            auth.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            for (Permissions permission : role.getPermissions()) {
                auth.add(new SimpleGrantedAuthority(permission.name()));
            }
        }
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
