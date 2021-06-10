package com.example.Hazelcastdemo.controller.user;

import com.example.Hazelcastdemo.commandObject.user.UserCO;
import com.example.Hazelcastdemo.service.user.UserService;
import com.example.Hazelcastdemo.valueObject.apiResponse.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> fetchAll() {
        return ResponseEntity.ok(new ApiResponseVO("SUCCESS", " Fetch All Users", userService.fetchALl()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> fetch(@PathVariable Integer userId) {
        return ResponseEntity.ok(new ApiResponseVO("SUCCESS", " Fetched user", userService.fetch(userId)));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updated(@RequestBody UserCO userCO, @PathVariable Integer userId) {
            return ResponseEntity.ok(new ApiResponseVO("SUCCESS", "user is Updated", userService.update(userCO, userId)));
    }

    @DeleteMapping("/{userUID}")
    public ResponseEntity<?> delete(@PathVariable Integer userUID) {
        userService.delete(userUID);
        return ResponseEntity.ok(new ApiResponseVO("SUCCESS", "user Deleted", null));

    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody UserCO userCO) {
        System.out.println("::::::::::::::::"+userCO);
            return ResponseEntity.ok(new ApiResponseVO("SUCCESS", "user is created", userService.create(userCO)));

    }
}
