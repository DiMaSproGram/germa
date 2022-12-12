package org.example.repository;

import org.example.entity.Order;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

//    @Procedure("SumCount")
//    List sumCount();
}
