package com.example.OrderService.repository;


import com.example.OrderService.entity.Orderss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orderss, Long> {
}
