package com.scs.web.blog.service;

        import com.scs.web.blog.domain.UserDto;
        import com.scs.web.blog.factory.ServiceFactory;
        import org.junit.Test;

        import java.sql.SQLException;
        import java.util.HashMap;
        import java.util.Map;

        import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService = ServiceFactory.getUserServiceInstance();
    @Test
    public void signIn() {
        Map<String, Object> map = new HashMap<>();
        UserDto userDto = new UserDto("13333333333", "11111111");
        map = userService.signIn(userDto);
        System.out.println(map.get("msg"));
        System.out.println(map.get("data"));
    }

    @Test
    public void insertIn() throws SQLException {
        Map<String, Object> map = new HashMap<>();
        UserDto userDto = new UserDto("13333333333","11111111");
        map = userService.insertIn(userDto);
        System.out.println(map.get("msg"));
    }
}