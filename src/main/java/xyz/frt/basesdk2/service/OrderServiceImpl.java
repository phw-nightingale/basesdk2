package xyz.frt.basesdk2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.basesdk2.common.Result;
import xyz.frt.basesdk2.entity.Order;
import xyz.frt.basesdk2.mapper.BaseMapper;
import xyz.frt.basesdk2.mapper.OrderMapper;

@Service
@Transactional
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public BaseMapper<Order> getMapper() {
        return orderMapper;
    }

}
