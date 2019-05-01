package xyz.frt.basesdk2.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.frt.basesdk2.entity.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}