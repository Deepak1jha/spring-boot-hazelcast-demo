package com.example.Hazelcastdemo.service.user;

import com.example.Hazelcastdemo.commandObject.user.UserCO;
import com.example.Hazelcastdemo.model.user.User;
import com.example.Hazelcastdemo.repository.user.UserRepository;
import com.example.Hazelcastdemo.util.UserNotFoundException;
import com.example.Hazelcastdemo.valueObject.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(cacheNames = {"UserCache"})
    public List<UserVO> fetchALl() {
        List<UserVO> userVOList = new ArrayList<>();
        Iterable<User> userIterable = userRepository.findAll();
        userIterable.forEach(user -> userVOList.add(new UserVO(user)));
        return userVOList;
    }

    @Cacheable(value = "UserCache" , key="#p0",unless = "#result==null")
    public UserVO fetch(Integer userId) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with this UID" + userId));
        return new UserVO(user);
    }

    //If we delete anything from DB it will also effect cache
    @CacheEvict(value = "UserCache")
    public String delete(Integer userId) {
       userRepository.deleteById(userId);
       return "User deleted with id " +userId;
    }

    @CachePut(value = "UserCache",  key="#p0", unless = "#result==null")
    public UserVO create(UserCO userCO) {
        User user = new User(userCO);
        userRepository.save(user);
        return new UserVO(user);
    }

    @CachePut(value = "UserCache",  key="#p0", unless = "#result==null")
    public UserVO update(UserCO userCO, Integer userId ) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with this UID" + userId));
        user.setUsername(userCO.getUsername());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        userRepository.save(user);
        return new UserVO(user);
    }
}
