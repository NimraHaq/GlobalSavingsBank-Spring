package com.lombok.lombok.service;

import com.lombok.lombok.dao.UserDao;
import com.lombok.lombok.dto.CustomerDto;
import com.lombok.lombok.entity.User;

public class UserServiceImpl implements UserService{
    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User addUser(CustomerDto customer) {
        return  null;
    }

//
//    private User customerToUserMapping(CustomerDto customerDto){
//        User user = User.builder().id()
//    }



}
