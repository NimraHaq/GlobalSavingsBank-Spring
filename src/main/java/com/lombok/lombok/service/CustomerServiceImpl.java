package com.lombok.lombok.service;

import com.lombok.lombok.dao.CustomerDao;
import com.lombok.lombok.dto.CustomerDto;
import com.lombok.lombok.dto.UserDto;
import com.lombok.lombok.entity.Customer;
import com.lombok.lombok.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService{
    CustomerDao customerDao;
    UserService userService;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, UserService userService) {
        this.customerDao = customerDao;
        this.userService = userService;
    }

    @Override
    public UserDto addCustomer(UserDto userDto) {
        userDto.setIsActive(Constants.NOT_ACTIVE);
        userDto.setRole(Constants.CUSTOMER_ROLE);
        userDto.setCustomerDto(new CustomerDto());
        userDto.getCustomerDto().setIsActive(Constants.NOT_ACTIVE);
        return userService.addUser(userDto);
    }

    protected static Customer customerDtoToEntityMapping(CustomerDto customerDto){
        Customer customer = Customer.builder().chId(customerDto.getChId()).defaultCardSrno(customerDto.getDefaultCardSrno())
                .registeredCards(customerDto.getRegisteredCards()).isActive(customerDto.getIsActive())
                .createdOn(customerDto.getCreatedOn()).lastUpdatedOn(customerDto.getLastUpdatedOn())
                .build();
        return customer;
    }
    protected static CustomerDto customerEntityToDtoMapping(Customer customer){
        CustomerDto customerDto = CustomerDto.builder().chId(customer.getChId()).defaultCardSrno(customer.getDefaultCardSrno())
                .registeredCards(customer.getRegisteredCards()).isActive(customer.getIsActive())
                .createdOn(customer.getCreatedOn()).lastUpdatedOn(customer.getLastUpdatedOn())
                .build();
        return customerDto;
    }
}
