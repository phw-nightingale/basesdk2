package xyz.frt.basesdk2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.basesdk2.common.JsonResult;
import xyz.frt.basesdk2.entity.User;
import xyz.frt.basesdk2.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public JsonResult signIn(@RequestBody User user) {
        return JsonResult.response(userService.signIn(user));
    }

}
