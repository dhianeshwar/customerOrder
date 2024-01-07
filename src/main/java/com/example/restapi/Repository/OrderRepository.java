package com.example.restapi.Repository;

import com.example.restapi.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {
    public List<Orders> findByTotalAmountGreaterThan(int amount);
    public List<Orders> findByTotalAmountLessThan(int amount);

}
