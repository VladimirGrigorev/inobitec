package com.inobitec.project.data.mapper;

import com.inobitec.project.data.dto.UserDto;
import com.inobitec.project.data.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper{

    UserDto userToUserDto(User user);
    List<UserDto> userToUserDto(List<User> users);
}
