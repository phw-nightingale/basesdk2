package xyz.frt.basesdk2.service;

import xyz.frt.basesdk2.common.Result;
import xyz.frt.basesdk2.entity.Order;

public interface SecKillService {

    /**
     * 开启秒杀
     * 1.将涉及的读写操作频率高的表存入缓存
     * 2.将redis作为临时数据库，所有的读写操作基于redis，
     * 待秒杀结束后再将缓存内容更新到数据库
     * @return 结果
     */
    public Result enableSecKill();

    /**
     * 执行秒杀
     * @param order 订单参数
     * @return 结果
     */
    public Result executeSecKill(Order order);

    /**
     * 关闭秒杀
     * @return 结果
     */
    public Result disableSecKill();
}
