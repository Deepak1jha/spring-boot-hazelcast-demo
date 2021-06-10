package com.example.Hazelcastdemo.repository.user;

import com.example.Hazelcastdemo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserById(Integer id);


}
