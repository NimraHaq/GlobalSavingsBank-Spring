package com.vault.service;

import com.vault.dao.CustomerDao;
import com.vault.dto.CustomerDto;
import com.vault.dto.UserDto;
import com.vault.entity.Customer;
import com.vault.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        userDto.setIsActive(Constants.OPTION_NO);
        userDto.setRole(Constants.CUSTOMER_ROLE);
        userDto.setCustomerDto(new CustomerDto());
        userDto.getCustomerDto().setIsActive(Constants.OPTION_NO);
        return userService.addUser(userDto);
    }

    @Override
    public CustomerDto getCustomerByChId(int chId) {
        return customerEntityToDtoMapping(customerDao.findCustomerByChId(chId));
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
