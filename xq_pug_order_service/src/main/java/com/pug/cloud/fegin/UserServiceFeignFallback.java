package com.pug.cloud.fegin;

import com.pug.cloud.domain.User;
import org.springframework.stereotype.Component;

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
        return  user1;
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
}
