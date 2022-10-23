package com.pug.cloud.fegin;

import com.pug.cloud.domain.User;
import com.pug.cloud.fegin.feginInterface.UserServiceFeign;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceFeignFallback implements UserServiceFeign {

    /**
     * 兜底方案
     * @param user
     * @return
     */
    @Override
    public User saveUser(User user) {
        User user1 = new User();
        user1.setUserId(-1000L);
        user1.setUsername("fallback提示");
        return user1;
    }

    /**
     * 兜底方案
     * @param userId
     * @return
     */
    @Override
    public User getUserInfo(Long userId) {
        User user1 = new User();
        user1.setUserId(-1000L);
        user1.setUsername("fallback提示");
        return  user1;
    }

    @Override
    public List<User> getUserInfoList(User user) {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUserId(100L);
        user1.setUsername("fallback提示");
        user1.setPassword("123456");
        user1.setAddress("fallback提示");
        userList.add(user1);
        return userList;
    }
}
