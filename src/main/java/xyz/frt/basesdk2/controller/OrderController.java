package xyz.frt.basesdk2.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.basesdk2.common.JsonResult;
import xyz.frt.basesdk2.entity.Order;
import xyz.frt.basesdk2.service.SecKillService;

@RestController
public class OrderController {

    private final SecKillService secKillService;

    public OrderController(SecKillService secKillService) {
        this.secKillService = secKillService;
    }

    @PatchMapping("/sec-kill/enable")
    public JsonResult enableSecKill() {
        return JsonResult.response(secKillService.enableSecKill());
    }

    @PatchMapping("/sec-kill/disable")
    public JsonResult disableSecKill() {
        return JsonResult.response(secKillService.disableSecKill());
    }

    @PostMapping("/orders")
    public JsonResult execSecKill(@RequestBody Order order) {
        return JsonResult.response(secKillService.executeSecKill(order));
    }

}
