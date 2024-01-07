package com.example.restapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ProductResponse {
    private long productId;
    private String productName;
    private int prize;


}
