package xyz.frt.basesdk2.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.frt.basesdk2.entity.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}