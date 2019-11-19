package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.Message;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author suyuxi
 * @className UserServiceImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {
        private UserDao userDao = DaoFactory.getUserDaoInstance();
        private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

        @Override
        public Map<String, Object> signIn(UserDto userDto) {
                User user = null;
                Map<String, Object> map = new HashMap<>();
                try {
                        user = userDao.findUserByMobile(userDto.getMobile());
                } catch (SQLException e) {
                        logger.error("根据手机号查询用户出现异常");
                }
                if (user != null) {
                        if (user.getPassword().equals(DigestUtils.md5Hex(userDto.getPassword()))) {
                                map.put("msg", Message.SIGN_IN_SUCCESS);
                                map.put("data", user);
                        } else {
                                map.put("msg", Message.PASSWORD_ERROR);
                        }
                } else {
                        map.put("msg", Message.MOBILE_NOT_FOUND);
                }
                return map;
        }

        @Override
        public Map<String, Object> insertIn(UserDto userDto) throws SQLException {
                User user = null;
                Map<String, Object> map = new HashMap<>();
                try{
                        user = userDao.findUserByMobile(userDto.getMobile());
                } catch (SQLException e) {
                        logger.error("根据手机号查询用户出现异常");
                }
                if (user != null){
                       map.put("msg", Message.MOBILE_NOT_ONLY);
                }else {
                      int i = DaoFactory.getUserDaoInstance().insert(userDto);
                      if (i != 0){
                              map.put("msg",Message.INSERT_SUCCESS);
                              map.put("data",userDto);
                              logger.info("注册成功");
                      }else {
                              logger.error("注册失败");
                      }
                }
                return map;
        }
}