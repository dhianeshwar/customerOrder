package com.example.restapi.DTO;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponse {
    private int orderId;
    private int Amount;
    private List<String> products;
}
