package com.example.restapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private Integer customerId;
    private String name;
    private int age;
    private String district;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Orders> ordersList;

}
