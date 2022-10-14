package com.pug.cloud.fegin.feginInterface;

import com.pug.cloud.domain.User;
import com.pug.cloud.fegin.FeignLoggerConfiguration;
import com.pug.cloud.fegin.UserServiceFeignFallback;
import com.pug.cloud.service.IUserContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value="xq-pug-user-service", configuration = FeignLoggerConfiguration.class, fallback = UserServiceFeignFallback.class)
public interface UserServiceFeign extends IUserContract {


    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping("/user/save")
    @Override
    User saveUser(@RequestBody User user);


    /**
     * 根据用户ID查询用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/user/get/{id}")
    @Override
    User getUserInfo(@PathVariable("id") Long userId);

    @GetMapping("/user/getUserInfoList")
    @Override
    List<User> getUserInfoList(@SpringQueryMap User user);

}
