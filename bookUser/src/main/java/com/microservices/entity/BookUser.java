package com.microservices.entity;

import com.microservices.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique=true)
    private String email;
    private String password;
    private String role;

    public BookUser convert(UserVO vo){
        this.id = vo.getId();
        this.name = vo.getName();
        this.email = vo.getEmail();
        this.password = vo.getPassword();
        this.role = vo.getRole();
        return this;
    }
}
