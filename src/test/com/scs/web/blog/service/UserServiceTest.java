package com.scs.web.blog.service;

        import com.scs.web.blog.domain.dto.UserDto;
        import com.scs.web.blog.factory.DaoFactory;
        import com.scs.web.blog.factory.ServiceFactory;
        import com.scs.web.blog.util.Result;
        import org.junit.Test;

        import java.sql.SQLException;
        import java.util.HashMap;
        import java.util.Map;

public class UserServiceTest {

    private UserService userService = ServiceFactory.getUserServiceInstance();
    @Test
    public void signIn() {
//        Map<String, Object> map = new HashMap<>();
        UserDto userDao = new UserDto();
        userDao.setMobile("13457513856");
        userDao.setPassword("111111");
        Result result = userService.signIn(userDao);
        System.out.println("code:" + result.getCode() + "," + "msg:" + result.getMsg()+",用户数据:"+result.getData());
//        UserDto userDto = new UserDto("13333333333", "11111111");
//        map = userService.signIn(userDto);
//        System.out.println(map.get("msg"));
//        System.out.println(map.get("data"));
    }

    @Test
    public void insertIn() throws SQLException {
//        Map<String, Object> map;
//        UserDto userDto = new UserDto("13333331111","11111111");
//        map = userService.insertIn(userDto);
//        System.out.println(map.get("msg"));
//        System.out.println(map.get("data"));
    }

    @Test
    public void getHotUsers() {
        Result rs = ServiceFactory.getUserServiceInstance().getHotUsers();
        System.out.println("用户数据："+rs.getData());
    }

    @Test
    public void selectByPage() {
        Result rs = ServiceFactory.getUserServiceInstance().selectByPage(8,6);
        System.out.println("数据"+ rs.getData());

    }

    @Test
    public void getUser() {
        Result rs = ServiceFactory.getUserServiceInstance().getUser(66);
        System.out.println("数据"+rs.getData());
    }

    @Test
    public void selectByKeywords() {
        Result rs = ServiceFactory.getUserServiceInstance().selectByKeywords("过");
        System.out.println("数据"+rs.getData());
    }
}