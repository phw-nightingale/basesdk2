package xyz.frt.basesdk2.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import xyz.frt.basesdk2.Basesdk2ApplicationTests;
import xyz.frt.basesdk2.entity.Product;

import java.util.Set;

@Slf4j
public class RedisTemplateServiceTest extends Basesdk2ApplicationTests {

    @Autowired
    private RedisTemplateService redisTemplateService;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testGetAll() {
        String pattern = "order-*";
        Set<Product> products = redisTemplateService.getAll(pattern, Product.class);
        for (Product p: products) {
            log.error(p.getName());
        }
    }

    @Test
    public void testClear() {
        redisTemplateService.clear();
    }

}
