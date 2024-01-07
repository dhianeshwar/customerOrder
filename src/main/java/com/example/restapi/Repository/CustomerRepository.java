package com.example.restapi.Repository;

import com.example.restapi.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
         List<Customer> findByName(String name);
         List<Customer> findByDistrict(String district);
}
