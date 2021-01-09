package com.inobitec.project.controller;

import com.inobitec.project.data.dto.security.AuthResDto;
import com.inobitec.project.data.dto.security.RegisterUserDto;
import com.inobitec.project.security.JwtProvider;
import com.inobitec.project.service.UserService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/api/v1/security")
public class SecurityController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public SecurityController(UserService userService,
                              JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public void register(@RequestBody RegisterUserDto dto) {
        userService.registerUser(dto, "ROLE_USER");
    }

    @PostMapping("/register/admin")
    @ResponseStatus(CREATED)
    public void registerAdmin(@RequestBody RegisterUserDto dto) {
        userService.registerUser(dto, "ROLE_ADMIN");
    }

    @PostMapping("/login")
    public AuthResDto login(@RequestBody RegisterUserDto dto) {
        var user = userService.findByLoginAndPassword(dto.getLogin(), dto.getPassword());
        if (user == null) {
            throw new RuntimeException("Invalid username or password.");
        }
        var token = jwtProvider.generateToken(user.getName(), user.getRoles());
        return new AuthResDto(token);
    }
}
