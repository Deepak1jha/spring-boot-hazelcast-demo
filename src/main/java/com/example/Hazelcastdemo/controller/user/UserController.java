package com.example.Hazelcastdemo.controller.user;

import com.example.Hazelcastdemo.config.UserMapStore;
import com.example.Hazelcastdemo.commandObject.user.UserCO;
import com.example.Hazelcastdemo.model.user.User;
import com.example.Hazelcastdemo.service.user.UserService;
import com.example.Hazelcastdemo.valueObject.apiResponse.ApiResponseVO;
import com.example.Hazelcastdemo.valueObject.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapStore userMapStore;

    @GetMapping("/")
    public ResponseEntity<?> fetchAll() {
        userService.fetchAll();
//        List<Integer> idList =new ArrayList<>();
        log.info("---------------------------");
        log.info("---------------------------");

//        iterable.forEach(id->{
//            log.info(String.valueOf(id.getClass()));
//        });
//        log.info(">>>>idList"+idList);
        userMapStore.loadAllKeys();
        return ResponseEntity.ok(new ApiResponseVO("SUCCESS", " Fetch All Users", null));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> fetch(@PathVariable Integer userId) {
        User user = userMapStore.load(userId);
        if (user == null) {
            return ResponseEntity.ok(new ApiResponseVO("SUCCESS", " Fetched user", userService.fetch(userId)));
        }
        return ResponseEntity.ok(new ApiResponseVO("SUCCESS", " Fetched user", new UserVO(user)));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updated(@RequestBody UserCO userCO, @PathVariable Integer userId) {
        User user = userService.update(userCO, userId);
        userMapStore.delete(userId);
        userMapStore.store(userId, user);
        return ResponseEntity.ok(new ApiResponseVO("SUCCESS", "user is Updated", new UserVO(user)));
    }

    @DeleteMapping("/{userUID}")
    public ResponseEntity<?> delete(@PathVariable Integer userUID) {
        userMapStore.delete(userUID);
        userService.delete(userUID);
        return ResponseEntity.ok(new ApiResponseVO("SUCCESS", "user Deleted", null));
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody UserCO userCO) {
        User user = userService.create(userCO);
        userMapStore.store(user.getId(), user);
        return ResponseEntity.ok(new ApiResponseVO("SUCCESS", "user is created", new UserVO(user)));
    }

}
