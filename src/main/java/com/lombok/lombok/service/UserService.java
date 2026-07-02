package com.lombok.lombok.service;

import com.lombok.lombok.dto.UserDto;
import com.lombok.lombok.entity.User;

import java.util.Optional;

public interface UserService {
    UserDto addUser(UserDto userDto);
    UserDto addAdmin(UserDto userDto);
    UserDto findUserByUsername(String username);
    UserDto deleteUserByUsernameAndCnic(String username, String cnic);
    UserDto findUserByUsernameAndCnic(String username, String cnic);
    void registerUser(UserDto user, String password);
}
