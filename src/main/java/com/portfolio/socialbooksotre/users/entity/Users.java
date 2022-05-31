package com.portfolio.socialbooksotre.users.entity;

import com.portfolio.socialbooksotre.commons.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "social_users")
public class Users extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id", updatable = false)
    private Long id;


    @Column(name = "id", unique = true, updatable = false)
    private Long name;

    private String password;

    @Column(unique = true)
    private String email;

    private String address;

    public Long getId() {
        return id;
    }

    public Long getName() {
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
    public Users(Long id, Long name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
