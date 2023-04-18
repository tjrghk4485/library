package com.codestates.excercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("from Order o left join fetch o.orderItems i left join fetch i.item where o.id =:orderId")
    Order findByIdAndOrderItemsAndItem(Long orderId);

}
