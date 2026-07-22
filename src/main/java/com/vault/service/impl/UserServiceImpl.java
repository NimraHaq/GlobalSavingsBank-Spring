package com.vault.service.impl;

import com.vault.dao.UserDao;
import com.vault.dto.CardDto;
import com.vault.dto.UserDto;
import com.vault.entity.User;
import com.vault.service.CardService;
import com.vault.service.UserService;
import com.vault.utils.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl extends UserService {

    UserDao userDao;
    CardService cardService;

    @Autowired
    public UserServiceImpl(UserDao userDao, CardService cardService) {
        this.userDao = userDao;
        this.cardService = cardService;
    }

    //method specifically for admin
    @Override
    public UserDto addAdmin(UserDto userDto) {
        userDto.setRole(Constants.ADMIN_ROLE);
        userDto.setIsActive(Constants.OPTION_NO);
        userDto.setCustomerDto(null);
        System.out.println("going to insert admin  " + userDto.getPassword());
        User user = userDao.save(userDtoToEntityMapping(userDto));
        return  userEntityToDtoMapping(user);
    }

    //method wrote generically for adding user
    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userDao.save(userDtoToEntityMapping(userDto));
        return  userEntityToDtoMapping(user);
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return userEntityToDtoMapping(userDao.findUserByUsername(username));
    }

    @Override
    public UserDto deleteUserByUsernameAndCnic(String username, String cnic) {
        Optional<User> user = userDao.findUserByUsernameAndCnic(username, cnic);
        if(user.isPresent()){
            userDao.delete(user.get());
            return userEntityToDtoMapping(user.get());
        }
        return null;
    }

    @Override
    public UserDto findUserByUsernameAndCnic(String username, String cnic) {

        Optional<User> user = userDao.findUserByUsernameAndCnic(username, cnic);
        if(user.isPresent()){
            return userEntityToDtoMapping(user.get());
        }
        return null;
    }

    //user is created by admin but password is not set, users registers themselves and set their password
    //setting user password
    @Transactional
    @Override
    public void registerUser(UserDto user, String password) {
        CardDto cardDto = null;
        if(user.getRole().equalsIgnoreCase(Constants.CUSTOMER_ROLE)){
            cardDto = cardService.addCard(user);
        }
        user.setIsActive(Constants.OPTION_YES);
        if(Objects.nonNull(user.getCustomerDto())){
            user.getCustomerDto().setIsActive(Constants.OPTION_YES);
            if(Objects.nonNull(cardDto)){
                user.getCustomerDto().setDefaultCardSrno(cardDto.getCardSrno());
                user.getCustomerDto().setRegisteredCards(1);
            }
        }
        user.setPassword(Constants.PASSWORD_HASHING_NOOP + password);
        userDao.save(userDtoToEntityMapping(user));
    }


    protected static UserDto userEntityToDtoMapping(User user){
        UserDto userDto = UserDto.builder().id(user.getId()).firstName(user.getFirstName())
                .lastName(user.getLastName()).username(user.getUsername()).isActive(user.getIsActive())
                .email(user.getEmail()).role(user.getRole()).createdOn(user.getCreatedOn()).
                lastUpdatedOn(user.getLastUpdatedOn()).age(user.getAge()).cnic(user.getCnic())
                .postalCode(user.getPostalCode()).address(user.getAddress()).phoneNumber(user.getPhoneNumber())
                .gender(user.getGender()).password(user.getPassword()).build();

        if(Objects.nonNull(user.getCustomer())){
            userDto.setCustomerDto(CustomerServiceImpl.customerEntityToDtoMapping(user.getCustomer()));
        }
        return userDto;
    }
    protected static User userDtoToEntityMapping(UserDto userDto){
        User user = User.builder().id(userDto.getId()).firstName(userDto.getFirstName())
                .lastName(userDto.getLastName()).username(userDto.getUsername()).isActive(userDto.getIsActive())
                .email(userDto.getEmail()).role(userDto.getRole()).createdOn(userDto.getCreatedOn()).
                lastUpdatedOn(userDto.getLastUpdatedOn()).age(userDto.getAge()).cnic(userDto.getCnic())
                .postalCode(userDto.getPostalCode()).address(userDto.getAddress()).phoneNumber(userDto.getPhoneNumber())
                .gender(userDto.getGender()).password(userDto.getPassword()).build();
        if(Objects.nonNull(userDto.getCustomerDto())){
            user.setCustomer(CustomerServiceImpl.customerDtoToEntityMapping(userDto.getCustomerDto()));
        }
        return user;
    }
}
