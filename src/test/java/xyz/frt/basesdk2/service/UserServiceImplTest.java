package xyz.frt.basesdk2.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.frt.basesdk2.Basesdk2ApplicationTests;
import xyz.frt.basesdk2.entity.User;

import java.util.List;

public class UserServiceImplTest extends Basesdk2ApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testSelectItemByExample() {
        User user = new User();
        user.setUsername("夜莺");
        List<User> users = userService.selectByExample(user);
        System.out.println(users.get(0).getUsername());
    }

}
