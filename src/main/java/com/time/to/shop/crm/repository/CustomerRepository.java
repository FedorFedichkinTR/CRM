package com.time.to.shop.crm.repository;

import com.time.to.shop.crm.model.db.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findAll();
}
