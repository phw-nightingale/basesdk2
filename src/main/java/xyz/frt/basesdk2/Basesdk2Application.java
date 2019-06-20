package xyz.frt.basesdk2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.basesdk2.common.JsonResult;
import xyz.frt.basesdk2.util.FileListener;

import java.io.IOException;

@RestController
@SpringBootApplication
public class Basesdk2Application {

    public static void main(String[] args) {
        SpringApplication.run(Basesdk2Application.class, args);
    }

    @GetMapping("/")
    public JsonResult home() {
        return JsonResult.success("Welcome to Application!");
    }

    @GetMapping("/404")
    public JsonResult _404() {
        return JsonResult.error(404, "request not found");
    }

    @GetMapping("/403")
    public JsonResult _403() {
        return JsonResult.error(403, "unauthorized");
    }

    @GetMapping("/401")
    public JsonResult _401() {
        return JsonResult.error(401, "token invalid");
    }
}
