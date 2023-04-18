package com.codestates.excercise;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, DslOrderItemRepository {

    OrderItem findByOrder(Order order);

    OrderItem findByOrderId(Long orderId);
}
