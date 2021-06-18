package com.example.Hazelcastdemo.commandObject.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String username;

}
