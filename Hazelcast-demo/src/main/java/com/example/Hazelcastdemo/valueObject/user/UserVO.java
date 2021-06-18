package com.example.Hazelcastdemo.valueObject.user;

import com.example.Hazelcastdemo.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String username;

    public UserVO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
    }

}
