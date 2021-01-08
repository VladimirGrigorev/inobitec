package com.inobitec.project.service;

import com.inobitec.project.data.dto.security.RegisterUserDto;
import com.inobitec.project.data.entity.User;
import com.inobitec.project.data.repository.RoleRepository;
import com.inobitec.project.data.repository.UserRepository;
import com.inobitec.project.exeption.WrongDataException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void registerUser(RegisterUserDto dto, String roleName) {
        if(userRepository.findByName(dto.getLogin()) != null)
            throw new WrongDataException();
        if(roleRepository.findByName(roleName) == null)
            throw new WrongDataException();
        var userRole = roleRepository.findByName(roleName);
        var user = new User();
        user.setName(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Collections.singletonList(userRole));
        userRepository.save(user);
    }

    public User findByLoginAndPassword(String login, String password) {
        var user = userRepository.findByName(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
