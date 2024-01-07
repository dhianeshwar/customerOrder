package com.example.restapi.Service;

import com.example.restapi.DTO.CustomerResponse;
import com.example.restapi.Model.Customer;

import java.util.List;

 public interface CustomerServiceInterface {
     Customer insertCustomer(Customer customer);
     Customer getCustomer(Integer id);
     CustomerResponse getCustomerResponse(Integer id);
     List<CustomerResponse> getAllCustomers();
     List<CustomerResponse> getCustomerByDistrict(String district);
     List<CustomerResponse> getCustomerByName(String name);
    
}
