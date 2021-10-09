package com.example.demo.appuser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity(name = "AppUser")
@Table(name = "appuser")
@Getter
@Setter
@NoArgsConstructor
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    @Column(
            name = "user_id",
            nullable = false
    )
    private Long id;

    @Column(
            name = "user_name",
            nullable = false,
            columnDefinition = "TEXT",
            length = 50
    )
    private String name;

    @Column(
            name = "username",
            unique = true,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;

    @Column(
            name = "user_email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "user_password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @Column(
            name = "user_role",
            nullable = false
    )
    private AppUserRole appUserRole;

    @Column(
            name = "account_locked",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private boolean locked;

    @Column(
            name = "account_expired",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private boolean expired;

    @Column(
            name = "account_enabled",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private boolean enabled;

    @Column(
            name = "user_credentials_expired",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private boolean credentialsExpired;

    public AppUser(String name,
                   String username,
                   String email,
                   String password,
                   AppUserRole appUserRole,
                   boolean locked,
                   boolean expired,
                   boolean enabled,
                   boolean credentialsExpired) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.locked = locked;
        this.expired = expired;
        this.enabled = enabled;
        this.credentialsExpired = credentialsExpired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AppUser))
            return false;
        AppUser appUser = (AppUser) o;
        return username.equals(appUser.username) && email.equals(appUser.email);
    }

    @Override
    public int hashCode() {
        /*
            A nice property of 31 is that the multiplication
            can be replaced by a shift and a subtraction for better performance
            on some architectures : 31 * i == (i << 5) - i
         */
        return 31 * username.hashCode() + email.hashCode();
    }
}
