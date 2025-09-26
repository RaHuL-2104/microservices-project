package com.rahul.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rahul.orderservice.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
