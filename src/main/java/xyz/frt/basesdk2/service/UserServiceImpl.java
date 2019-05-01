package xyz.frt.basesdk2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.basesdk2.common.AppConst;
import xyz.frt.basesdk2.common.Result;
import xyz.frt.basesdk2.entity.User;
import xyz.frt.basesdk2.mapper.BaseMapper;
import xyz.frt.basesdk2.mapper.UserMapper;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public BaseMapper<User> getMapper() {
        return userMapper;
    }

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Result signIn(User user) {
        var clause = user.createClause();
        User temp;
        List<User> users;
        if (user.getPassword() == null) {
            return Result.response(AppConst.STATUS_ERROR, "密码不能为空");
        }
        if (user.getToken() != null) {
            clause.withColumnEqualTo("token", user.getToken());
        } else if (user.getUsername() != null) {
            clause.withColumnEqualTo("username", user.getUsername());
        } else if (user.getEmail() != null) {
            clause.withColumnEqualTo("email", user.getEmail());
        } else if (user.getPhone() != null) {
            clause.withColumnEqualTo("phone", user.getPhone());
        } else {
            return Result.response(AppConst.STATUS_ERROR, "用户名/手机号/邮箱不能为空");
        }
        users = selectByExample(user);
        if (users.size() == 1) {
            temp = users.get(0);
        } else {
            return Result.response(AppConst.STATUS_ERROR);
        }
        if (temp.getPassword().equals(user.getPassword())) {
            //更新token
            temp.setToken(UUID.randomUUID().toString());
            temp.setLastLoginTime(new Date());
            temp.setExpire(new Date(System.currentTimeMillis() + 60 * 30 * 1000));
            updateByPrimaryKey(temp);
            log.debug("user [" + temp.getUsername() + "] login...");
            return Result.response(AppConst.STATUS_SUCCESS, "登录成功", temp);
        } else {
            return Result.response(AppConst.STATUS_ERROR, "密码错误");
        }
    }
}
