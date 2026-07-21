package com.vault.dao;

import com.vault.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "customers")
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    Customer findCustomerByChId(int chId);
}
