package com.lombok.lombok.dao;

import com.lombok.lombok.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "customers")
public interface CustomerDao extends JpaRepository<Customer, Integer> {
}
