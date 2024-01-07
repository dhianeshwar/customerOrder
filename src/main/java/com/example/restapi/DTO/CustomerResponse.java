package com.example.restapi.DTO;

import com.example.restapi.Model.Customer;
import com.example.restapi.Model.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CustomerResponse {
    private int id;
    private String name;
//    private List<Orders> ordersList;
}
