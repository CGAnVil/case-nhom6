package com.codegym.controller;

import com.codegym.model.UserInfo;
import com.codegym.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user-infos")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping
    public ResponseEntity<Iterable<UserInfo>> getAllUserInfo() {
        return new ResponseEntity<>(userInfoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable Long id) {
        Optional<UserInfo> userInfoOptional = userInfoService.findById(id);
        return userInfoOptional.map(userInfo -> new ResponseEntity<>(userInfo, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfo userInfo) {
        return new ResponseEntity<>(userInfoService.save(userInfo), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInfo> updateUserInfo(@PathVariable Long id, @RequestBody UserInfo userInfo) {
        Optional<UserInfo> userInfoOptional = userInfoService.findById(id);
        return userInfoOptional.map(userInfo1 -> {
            userInfo.setId(userInfo1.getId());
            userInfoService.save(userInfo);
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserInfo> deleteUserInfo(@PathVariable Long id) {
        Optional<UserInfo> userInfoOptional = userInfoService.findById(id);
        return userInfoOptional.map(userInfo -> {
            userInfoService.removeById(id);
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
