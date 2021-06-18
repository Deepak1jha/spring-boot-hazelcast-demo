package com.example.Hazelcastdemo.bootstrap;

import com.example.Hazelcastdemo.config.UserMapStore;
import com.example.Hazelcastdemo.model.user.User;
import com.example.Hazelcastdemo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class Bootstrap implements InitializingBean {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapStore userMapStore;

    @Override
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        log.info("***************** Load Stat *************");
        Map<Integer, User> userMap = new HashMap<Integer,User>();
        List<User> list =userService.fetchAll();
        list.forEach(user -> userMap.put(user.getId(),user));
        userMapStore.storeAll(userMap);
    }
}
