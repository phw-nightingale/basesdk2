package xyz.frt.basesdk2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.basesdk2.entity.OrderDetail;
import xyz.frt.basesdk2.mapper.BaseMapper;
import xyz.frt.basesdk2.mapper.OrderDetailMapper;

@Service
@Transactional
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {

    private final OrderDetailMapper orderDetailMapper;

    public OrderDetailServiceImpl(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public BaseMapper<OrderDetail> getMapper() {
        return orderDetailMapper;
    }
}
