package xyz.frt.basesdk2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.basesdk2.common.AppConst;
import xyz.frt.basesdk2.common.AppContext;
import xyz.frt.basesdk2.common.Result;
import xyz.frt.basesdk2.entity.Order;
import xyz.frt.basesdk2.entity.OrderDetail;
import xyz.frt.basesdk2.entity.Product;
import xyz.frt.basesdk2.entity.User;
import xyz.frt.basesdk2.redis.RedisTemplateService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class SecKillServiceImpl implements SecKillService {

    private final OrderService orderService;

    private final OrderDetailService orderDetailService;

    private final ProductService productService;

    private final RedisTemplateService redisTemplateService;

    private static int isEnable = 0;

    private static final String KEY_ORDER = "order-";
    private static final String KEY_ORDER_DETAIL = "order-detail-";
    private static final String KEY_PRODUCT = "product-";

    public SecKillServiceImpl(OrderService orderService,
                              ProductService productService,
                              OrderDetailService orderDetailService,
                              RedisTemplateService redisTemplateService) {
        this.orderService = orderService;
        this.productService = productService;
        this.orderDetailService = orderDetailService;
        this.redisTemplateService =redisTemplateService;
    }

    @Override
    public Result enableSecKill() {
        User user = AppContext.getCurrentUser();

        if (isEnable == 0) {
            List<Product> products = productService.selectAll();
            for (Product p: products) {
                redisTemplateService.set("product-" + p.getId(), p);
            }
            isEnable = 1;
        } else {
            return Result.response(AppConst.STATUS_ERROR, "系统已开启，请勿重复操作");
        }
        return Result.response(AppConst.STATUS_SUCCESS, "开启完成");
    }

    @Override
    public Result disableSecKill() {
        long start = System.currentTimeMillis();
        int sum = 0;
        if (isEnable == 1) {
            //更新缓存中的数据到数据库
            Set<Product> pSet = redisTemplateService.getAll(KEY_PRODUCT + "*", Product.class);
            for (Product p : pSet) {
                productService.updateByPrimaryKey(p);
            }
            Set<Order> oSet = redisTemplateService.getAll(KEY_ORDER + "*", Order.class);
            for (Order o : oSet) {
                orderService.updateByPrimaryKey(o);
            }
            Set<OrderDetail> odSet = redisTemplateService.getAll(KEY_ORDER_DETAIL + "*", OrderDetail.class);
            for (OrderDetail od : odSet) {
                orderDetailService.insert(od);
            }
            //清理缓存
            redisTemplateService.clear();
            sum = pSet.size() + odSet.size() + odSet.size();
            isEnable = 0;
        } else {
            return Result.response(AppConst.STATUS_ERROR, "系统已关闭，请勿重复操作");
        }
        long end = System.currentTimeMillis();
        long used = (end - start) / 1000;
        return Result.response(AppConst.STATUS_SUCCESS, "关闭成功, 用时:" + used + "sec, 共影响:" + sum + "条数据");
    }

    @Override
    public Result executeSecKill(Order order) {
        if (isEnable == 1) {
            List<OrderDetail> orderDetails = order.getOrderDetails();
            orderService.insert(order);
            int totalMoney = 0;
            for (OrderDetail od : orderDetails) {
                //更新库存
                Product product = productService.selectByPrimaryKey(od.getProductId());
                if (product.getCount() < od.getCount()) {
                    return Result.response(AppConst.STATUS_ERROR, "[" + product.getName() + "]库存不足");
                }
                od.setProduct(product);
                od.setOrderId(order.getId());
                product.setCount(product.getCount() - od.getCount());
                redisTemplateService.set(KEY_PRODUCT + product.getId(), product);
                redisTemplateService.set(KEY_ORDER_DETAIL + UUID.randomUUID().toString(), od);
                totalMoney += od.getSumMoney();
            }
            order.setTotalMoney(totalMoney);
            //更新缓存
            redisTemplateService.set(KEY_ORDER + order.getId(), order);
            return Result.response(AppConst.STATUS_SUCCESS, "生成订单成功");
        } else {
            return Result.response(AppConst.STATUS_ERROR, "抢购还未开始");
        }
    }
}
