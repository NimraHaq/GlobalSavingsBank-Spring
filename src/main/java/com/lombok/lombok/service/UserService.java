package com.lombok.lombok.service;

import com.lombok.lombok.dto.CustomerDto;
import com.lombok.lombok.entity.User;

public interface UserService {
    User addUser(CustomerDto customerDto);
}
