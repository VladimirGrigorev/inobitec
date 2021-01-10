package com.inobitec.project.controller;

import com.inobitec.project.data.dto.UserDto;
import com.inobitec.project.data.mapper.UserMapper;
import com.inobitec.project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/me")
public class CurrentUserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public CurrentUserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(path = "")
    public UserDto getProfile(Authentication authentication) {
        String currentPrincipalName = authentication.getName();
        var user = userService.findByLogin(currentPrincipalName);
        return userMapper.userToUserDto(user);
    }
}
