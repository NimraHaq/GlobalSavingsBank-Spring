package com.lombok.lombok.service;

import com.lombok.lombok.dao.CustomerDao;
import com.lombok.lombok.dto.CustomerDto;
import com.lombok.lombok.entity.Customer;
import com.lombok.lombok.entity.User;
import com.lombok.lombok.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = mapDtoToEntity(customerDto);
        customer.setIsActive(Constants.NOT_ACTIVE);
        customer.setUser(User.builder().username(customerDto.getUsername()).password(customerDto.getPassword())
                .email(customerDto.getEmail()).firstName(customerDto.getFirstName()).lastName(customerDto.getLastName())
                .role(Constants.CUSTOMER_ROLE).isActive(Constants.NOT_ACTIVE).customer(customer).build());
        Customer customerFromDb = customerDao.save(customer);
        return mapEntityToDto(customerFromDb);
    }

    private Customer mapDtoToEntity(CustomerDto customerDto){
        Customer customer = Customer.builder().chId(customerDto.getChId()).firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName()).phoneNumber(customerDto.getPhoneNumber()).address(customerDto.getAddress())
                .gender(customerDto.getGender()).status(customerDto.getStatus()).cnic(customerDto.getCnic()).age(customerDto.getAge()).postalCode(customerDto.getPostalCode()).build();
        return customer;
    }
    private CustomerDto mapEntityToDto(Customer customer){
        CustomerDto customerDto = CustomerDto.builder().chId(customer.getChId()).firstName(customer.getFirstName())
                .lastName(customer.getLastName()).address(customer.getAddress()).phoneNumber(customer.getPhoneNumber())
                .isActive(customer.getIsActive()).gender(customer.getGender()).email(customer.getEmail())
                .createdOn(customer.getCreatedOn()).lastUpdatedOn(customer.getLastUpdatedOn())
                .age(customer.getAge()).status(customer.getStatus()).cnic(customer.getCnic()).postalCode(customer.getPostalCode()).build();
        return customerDto;
    }
}
