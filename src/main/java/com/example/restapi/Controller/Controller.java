package com.example.restapi.Controller;

import com.example.restapi.DTO.CustomerResponse;
import com.example.restapi.DTO.OrderResponse;
import com.example.restapi.DTO.ProductResponse;
import com.example.restapi.Model.Customer;
import com.example.restapi.Model.Orders;
import com.example.restapi.Model.Product;
import com.example.restapi.ServiceImpl.CustomerImpl;
import com.example.restapi.ServiceImpl.OrderImpl;
import com.example.restapi.ServiceImpl.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private ProductImpl productImpl;
    @Autowired
    private CustomerImpl customerImpl;
    @Autowired
    private OrderImpl orderimpl;

//    -----------------------------------PRODUCT--1----------------------------------------------

    @PostMapping("/insertProduct")
    public Product insertProduct(@RequestBody Product product)
    {
        return productImpl.insertProduct(product);
    }

    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId)
    {
        return  new ResponseEntity<>(productImpl.getProductResponse(productId),HttpStatus.OK);
    }

    @GetMapping("/getProductByName/{name}")
    public ResponseEntity<List<ProductResponse>> getProductByName(@PathVariable String name)
    {
        return new ResponseEntity<>(productImpl.getProductByName(name),HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductResponse>> getAllProducts()
    {
        return new ResponseEntity<>(productImpl.getAllProducts(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long productId)
    {
        return new ResponseEntity<>(productImpl.deleteProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/getProductByAmountLessThan/{prize}")
    public ResponseEntity<List<ProductResponse>> getProductByAmountLessThan(@PathVariable int prize)
    {
        return new ResponseEntity<>(productImpl.findProductLessThan(prize),HttpStatus.OK);
    }
//-----------------------------------------CUSTOMER----------------------------------------------------

    @PostMapping("/insertCustomer")
    public Customer insertCustomer(@RequestBody Customer customer)
    {
        return customerImpl.insertCustomer(customer);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers()
    {
        return new ResponseEntity<>(customerImpl.getAllCustomers(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getCustomer/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Integer customerId)
    {
        return new ResponseEntity<>(customerImpl.getCustomerResponse(customerId), HttpStatus.OK);
    }

    @GetMapping("/getCustomerByName/{customerName}")
    public ResponseEntity<List<CustomerResponse>> getCustomerByName(@PathVariable String customerName)
    {
        return new ResponseEntity<>(customerImpl.getCustomerByName(customerName), HttpStatus.OK);
    }

    @GetMapping("/getCustomerByDistrict/{district}")
    public ResponseEntity<List<CustomerResponse>> getCustomerByDistrict(@PathVariable String district)
    {
        return new ResponseEntity<>(customerImpl.getCustomerByDistrict(district), HttpStatus.OK);
    }

//--------------------------------------ORDERS-----------------------------------------------
    @PostMapping("/placeAnOrder")
    public ResponseEntity<OrderResponse> placeAnOrder(@RequestBody Orders orderRequest)
    {
        return new ResponseEntity<>(orderimpl.placeOrder(orderRequest),HttpStatus.OK);
    }

    @GetMapping("/getOrders")
    public ResponseEntity<List<OrderResponse>> getOrders()
    {
        return  new ResponseEntity<>(orderimpl.getAllOrders(),HttpStatus.OK);
    }

    @GetMapping("/getOrders/{orderId}")
    public ResponseEntity<OrderResponse> getOrders(@PathVariable int orderId)
    {
        return  new ResponseEntity<>(orderimpl.getOrderById(orderId),HttpStatus.OK);
    }

    @GetMapping("/getOrdersGreaterThan/{amount}")
    public ResponseEntity<List<OrderResponse>> getOrdersGreaterThan(@PathVariable int amount)
    {
        return  new ResponseEntity<>(orderimpl.getOrdersByAmountGreaterThan(amount),HttpStatus.OK);
    }

    @GetMapping("/getOrdersLesserThan/{amount}")
    public ResponseEntity<List<OrderResponse>> getOrdersLesserThan(@PathVariable int amount)
    {
        return  new ResponseEntity<>(orderimpl.getOrdersAmountLessThan(amount),HttpStatus.OK);
    }

}
