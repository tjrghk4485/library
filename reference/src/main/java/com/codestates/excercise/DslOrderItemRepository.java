package com.codestates.excercise;

import java.util.List;

public interface DslOrderItemRepository {

    List<OrderItemDto> findOrderItemsByOrderId(Long orderId);
}