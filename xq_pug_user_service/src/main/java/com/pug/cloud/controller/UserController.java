package com.pug.cloud.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.pug.cloud.domain.User;
import com.pug.cloud.service.IUserContract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RefreshScope // 配置文件实时刷新
@RequestMapping("/user")
public class UserController implements IUserContract {

    @Value("${uname}")
    private String uname;

    @Value("${server.port}")
    private String port;

    @GetMapping("/getUser")
    public String getUser() {
        return "uname:" + uname;
    }

    @GetMapping("/getUser2")
    public String getUser2() {
        return "port: " + port;
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Override
    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        User user1 = new User();
        user1.setUserId(10189L);
        user1.setUsername("yykk");
        user1.setPassword("123456");
        user1.setAddress("湖南长沙");
        return user1;
    }

    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    @Override
    @GetMapping("/get/{id}")
    public User getUserInfo(@PathVariable("id") Long userId) {
        log.info("调用用户服务");
        if (userId == 1L) {
            try {
                Thread.sleep(2000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        User user = new User();
        user.setUserId(userId);
        user.setUsername("yykk");
        user.setPassword("123456");
        user.setAddress("湖南长沙");
        return user;
    }

}