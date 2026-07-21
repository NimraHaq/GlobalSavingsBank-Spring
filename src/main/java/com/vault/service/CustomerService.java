package com.vault.service;

import com.vault.dto.CustomerDto;
import com.vault.dto.UserDto;

public interface CustomerService {
    UserDto addCustomer(UserDto userDto);
    CustomerDto getCustomerByChId(int chId);
}

