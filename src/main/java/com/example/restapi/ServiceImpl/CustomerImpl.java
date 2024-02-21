package com.example.restapi.ServiceImpl;
import com.example.restapi.DTO.CustomerResponse;
import com.example.restapi.Error.CustomerNotFoundException;
import com.example.restapi.Error.CustomerAlreadyExist;
import com.example.restapi.Model.Customer;
import com.example.restapi.Model.Product;
import com.example.restapi.Repository.CustomerRepository;
import com.example.restapi.Service.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerImpl implements CustomerServiceInterface {//-->In communicate
    @Autowired
    private CustomerRepository customerRepository;

    public Customer insertCustomer(Customer customer)
    {
        if(customerRepository.findById(customer.getCustomerId()).isPresent())
            throw new CustomerAlreadyExist("A Customer is already present with the provided Id");
        return customerRepository.save(customer);
    }
    public Customer getCustomer(Integer id)
    {
        return customerRepository.findById(id)
                .orElseThrow(()->
                new CustomerNotFoundException("No Customer with such id is available",
                        "Enter a valid Customer id and try again"));
    }
    public CustomerResponse getCustomerResponse(Integer id)
    {
        return entityToDtoConversion(getCustomer(id));
    }
    public List<CustomerResponse> getAllCustomers()
    {
        return customerRepository.findAll()
                .stream()
                .map(this::entityToDtoConversion)
                .toList();
    }

    public List<CustomerResponse> getCustomerByDistrict(String district)
    {
        return Optional.of(customerRepository.findByDistrict(district))
                .filter(customers -> !customers.isEmpty())
                .orElseThrow(() -> new CustomerNotFoundException("No Customer available in this district","Enter a Some other district and try again"))
                .stream()
                .map(this::entityToDtoConversion)
                .toList();
    }
    public List<CustomerResponse> getCustomerByName(String name)
    {
        return Optional.of(customerRepository.findByName(name))
                .filter(customers -> !customers.isEmpty())
                .orElseThrow(() ->new CustomerNotFoundException("No Customer available in this name","Try with some other Name"))
                .stream()
                .map(this::entityToDtoConversion)
                .toList();
    }
    private  CustomerResponse entityToDtoConversion(Customer customer)
    {
        return new CustomerResponse(customer.getCustomerId(),customer.getName());
    }
}
