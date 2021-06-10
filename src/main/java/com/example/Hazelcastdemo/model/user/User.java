package com.example.Hazelcastdemo.model.user;

import com.example.Hazelcastdemo.commandObject.user.UserCO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String username;

    public User(UserCO userCO) {
        this.id = userCO.getId();
        this.firstName = userCO.getFirstName();
        this.lastName = userCO.getLastName();
        this.username = userCO.getUsername();
    }
    
}

