package xyz.frt.basesdk2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.basesdk2.entity.Product;
import xyz.frt.basesdk2.mapper.BaseMapper;
import xyz.frt.basesdk2.mapper.ProductMapper;

@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public BaseMapper<Product> getMapper() {
        return productMapper;
    }
}
