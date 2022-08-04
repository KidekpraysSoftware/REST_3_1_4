package com.kata.kidek.rest.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
@Transactional
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles", cascade=CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
