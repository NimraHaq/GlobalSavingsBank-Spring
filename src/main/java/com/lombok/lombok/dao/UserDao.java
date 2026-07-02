package com.lombok.lombok.dao;

import com.lombok.lombok.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
    Optional<User> findUserByUsernameAndCnic(String username, String cnic);
}
