package com.kata.kidek.rest.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@Transactional
public class User implements UserDetails, Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private String age;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<Role> roles;

    public String rolesToString() {
        StringBuilder roles = new StringBuilder();
        Boolean switcher = false;
        for (Role role : this.roles) {
            if (!switcher) {
                roles.append(role.toString());
                switcher = true;
            } else {
                roles.append(", ");
                roles.append(role.toString());
                switcher = false;
            }
        }
        return roles.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + rolesToString() +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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

    public User() {
    }

    public User(String name, String lastName, String mail, String password) {
        this.firstname = name;
        this.lastname = lastName;
        this.email = mail;
        this.password = password;

    }

    public User(String mail, String password, String firstname, String lastname, String age, Set<Role> roles) {
        this.email = mail;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.roles = roles;
    }
}
