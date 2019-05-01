package xyz.frt.basesdk2.service;

import xyz.frt.basesdk2.common.Result;
import xyz.frt.basesdk2.entity.User;

public interface UserService extends BaseService<User> {

    public Result signIn(User user);
}
