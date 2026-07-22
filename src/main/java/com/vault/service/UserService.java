package com.vault.service;

import com.vault.dto.UserDto;
import com.vault.entity.User;

import java.util.Optional;

public abstract class UserService {
    public  abstract UserDto addUser(UserDto userDto);
    public  abstract UserDto addAdmin(UserDto userDto);
    public  abstract UserDto findUserByUsername(String username);
    public  abstract UserDto deleteUserByUsernameAndCnic(String username, String cnic);
    public  abstract UserDto findUserByUsernameAndCnic(String username, String cnic);
    public  abstract void registerUser(UserDto user, String password);
}
