package com.example.restapi.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String name;
    private int prize;
    private int availableCount;
    private int lastSoldCount;
    private int totalSoldCount;
    private int isAvailable=1;

    @JsonIgnore
    @ManyToMany(mappedBy = "productList")
    private List<Orders> orderList;

}
