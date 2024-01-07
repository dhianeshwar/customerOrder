package com.example.restapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Setter
@Getter
@AllArgsConstructor
public class OrderRequest {
    private int customerId;
    private Map<Integer,Integer> productAndCount;
}
