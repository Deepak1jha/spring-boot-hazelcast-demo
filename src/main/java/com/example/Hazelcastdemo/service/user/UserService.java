package com.example.Hazelcastdemo.service.user;

import com.example.Hazelcastdemo.commandObject.user.UserCO;
import com.example.Hazelcastdemo.model.user.User;
import com.example.Hazelcastdemo.repository.user.UserRepository;
import com.example.Hazelcastdemo.util.UserNotFoundException;
import com.example.Hazelcastdemo.valueObject.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> fetchAll() {
        List<User> userList = new ArrayList<>();
        Iterable<User> userIterable = userRepository.findAll();
        userIterable.forEach(userList::add);
        return userList;
    }

    public Iterable<Long> findAllId(){
        return userRepository.findAllId();
    }

    public UserVO fetch(Integer userId) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with this UID" + userId));
        return new UserVO(user);
    }

    //If we delete anything from DB it will also effect cache
    public void delete(Integer userId) {
       userRepository.deleteById(userId);
    }

    public User create(UserCO userCO) {
        User user = new User(userCO);
        return userRepository.save(user);
    }

    @CachePut(value = "UserCache",  key="#p0", unless = "#result==null")
    public User update(UserCO userCO, Integer userId ) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with this UID" + userId));
        user.setUsername(userCO.getUsername());
        user.setFirstName(userCO.getFirstName());
        user.setLastName(userCO.getLastName());
        userRepository.save(user);
        return user;
    }

}
