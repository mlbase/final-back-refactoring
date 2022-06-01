package com.portfolio.socialbooksotre.users.entity;

import com.portfolio.socialbooksotre.commons.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
@Getter
@NoArgsConstructor
@Table(name = "social_users")
public class Users extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id", updatable = false)
    private Long id;

    private String password;

    @Column(name = "id", unique = true, updatable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private String address;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Builder
    public Users(String password, String name, String email, String address) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Users(Long id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
