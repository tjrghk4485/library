package com.codestates.excercise;

import com.codestates.excercise.DslOrderItemRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.codestates.excercise.QItem.item;
import static com.codestates.excercise.QOrder.order;
import static com.codestates.excercise.QOrderItem.orderItem;

public class DslOrderItemRepositoryImpl implements DslOrderItemRepository {

    private final JPAQueryFactory queryFactory;

    public DslOrderItemRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<OrderItemDto> findOrderItemsByOrderId(Long orderId) {
        return queryFactory.select(new QOrderItemDto(
                        orderItem.orderPrice,
                        orderItem.count,
                        item.name
                ))
                .from(orderItem)
                .join(orderItem.order, order)
                .join(orderItem.item, item)
                .where(order.id.eq(orderId))
                .fetch();
    }
}