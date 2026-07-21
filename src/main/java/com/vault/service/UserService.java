package com.vault.service;

import com.vault.dto.UserDto;
import com.vault.entity.User;

import java.util.Optional;

public interface UserService {
    UserDto addUser(UserDto userDto);
    UserDto addAdmin(UserDto userDto);
    UserDto findUserByUsername(String username);
    UserDto deleteUserByUsernameAndCnic(String username, String cnic);
    UserDto findUserByUsernameAndCnic(String username, String cnic);
    void registerUser(UserDto user, String password);
}
