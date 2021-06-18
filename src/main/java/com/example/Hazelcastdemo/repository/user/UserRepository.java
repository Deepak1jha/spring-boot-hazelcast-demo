package com.example.Hazelcastdemo.repository.user;

import com.example.Hazelcastdemo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserById(Integer id);

    @Query("SELECT u.id from User u")
    Iterable<Long>findAllId();
}
