package com.lombok.lombok.service;

import com.lombok.lombok.dao.CustomerDao;
import com.lombok.lombok.dto.CustomerDto;
import com.lombok.lombok.entity.Customer;
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
        //add entry in user table as well.
        Customer customer = customerDao.save(mapDtoToEntity(customerDto));
        return mapEntityToDto(customer);
    }

    private Customer mapDtoToEntity(CustomerDto customerDto){
        Customer customer = Customer.builder().chId(customerDto.getChId()).firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName()).phoneNumber(customerDto.getPhoneNumber()).address(customerDto.getAddress())
                .gender(customerDto.getGender()).age(customerDto.getAge()).postalCode(customerDto.getPostalCode()).build();
        return customer;
    }
    private CustomerDto mapEntityToDto(Customer customer){
        CustomerDto customerDto = CustomerDto.builder().chId(customer.getChId()).firstName(customer.getFirstName())
                .lastName(customer.getLastName()).address(customer.getAddress()).phoneNumber(customer.getPhoneNumber())
                .isActive(customer.getIsActive()).gender(customer.getGender()).email(customer.getEmail())
                .createdOn(customer.getCreatedOn()).lastUpdatedOn(customer.getLastUpdatedOn())
                .age(customer.getAge()).postalCode(customer.getPostalCode()).build();
        return customerDto;
    }
}
