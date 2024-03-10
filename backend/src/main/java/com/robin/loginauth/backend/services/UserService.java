package com.robin.loginauth.backend.services;

import com.robin.loginauth.backend.config.UserAuthProvider;
import com.robin.loginauth.backend.dto.CredentialsDto;
import com.robin.loginauth.backend.dto.SignUpDto;
import com.robin.loginauth.backend.dto.UserDto;
import com.robin.loginauth.backend.entities.User;
import com.robin.loginauth.backend.exceptions.AppException;
import com.robin.loginauth.backend.mappers.UserMapper;
import com.robin.loginauth.backend.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto findByLogin(String login){

      User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

      return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto credentialsDto){
       User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));


        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST)
    }

public UserDto register(SignUpDto userDto) {
    Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());

    if(optionalUser.isPresent()) {
    throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
    }

   User user = userMapper.signUpToUser(userDto);

    user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

    User savedUser = userRepository.save(user);

    return userMapper.toUserDto(user);
    }

}