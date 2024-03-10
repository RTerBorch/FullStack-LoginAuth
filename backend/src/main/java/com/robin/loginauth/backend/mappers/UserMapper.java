package com.robin.loginauth.backend.mappers;

import com.robin.loginauth.backend.dto.SignUpDto;
import com.robin.loginauth.backend.dto.UserDto;
import com.robin.loginauth.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto userDto);

}
