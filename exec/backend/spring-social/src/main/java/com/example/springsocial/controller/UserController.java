package com.example.springsocial.controller;

import com.example.springsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.model.User;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getCurrentUser(userPrincipal);
    }
}
