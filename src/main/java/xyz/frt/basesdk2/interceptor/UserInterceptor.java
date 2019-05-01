package xyz.frt.basesdk2.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.frt.basesdk2.common.AppConst;
import xyz.frt.basesdk2.entity.Role;
import xyz.frt.basesdk2.entity.User;
import xyz.frt.basesdk2.redis.RedisTemplateService;
import xyz.frt.basesdk2.service.RoleService;
import xyz.frt.basesdk2.service.UserService;
import xyz.frt.basesdk2.util.BaseUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {

    private final RoleService roleService;

    private final UserService userService;

    private final RedisTemplateService redisTemplateService;

    @Autowired
    public UserInterceptor(RoleService roleService, UserService userService, RedisTemplateService redisTemplateService) {
        this.roleService = roleService;
        this.userService = userService;
        this.redisTemplateService = redisTemplateService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader(AppConst.KEY_AUTHORIZATION);
        if (BaseUtils.isNullOrEmpty(token)) {
            response.sendRedirect("/401");
            return false;
        }
        //先从缓存中查找
        User user;
        String key = "user-" + token;
        if (redisTemplateService.hasKey(key)) {
            user = redisTemplateService.get(key, User.class);

        } else {

            user = new User();
            User.Clause clause = user.createClause();
            clause.withColumnEqualTo("token", token);
            List<User> users = userService.selectByExample(user);
            if (users.size() != 1) {
                response.sendRedirect("/403");
            }
            user = users.get(0);
            //存入缓存
            redisTemplateService.set(key, user);
        }

        // 判断token是否过期
        if (new Date().getTime() > user.getExpire().getTime()) {
            response.sendRedirect("/401");
            return false;
        }

        user.setExpire(new Date(System.currentTimeMillis() + 60 * 30 * 1000));
        redisTemplateService.set(key, user);
        userService.updateByPrimaryKey(user);

        Role role = roleService.selectByPrimaryKey(user.getRoleId());
        user.setRole(role);
        request.setAttribute(AppConst.KEY_CURRENT_USER, user);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

}
