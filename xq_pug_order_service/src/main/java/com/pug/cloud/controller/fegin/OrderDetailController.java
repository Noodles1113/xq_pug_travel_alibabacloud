package com.pug.cloud.controller.fegin;

import com.pug.cloud.domain.User;
import com.pug.cloud.fegin.feginInterface.UserServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderDetailController {

    @Autowired
    private UserServiceFeign userServiceFeign;

    @GetMapping("/detail/{userId}")
    public User getUserInfo(@PathVariable("userId") Long userId) {
        log.info("采用fegin方式调用其他服务");
        return userServiceFeign.getUserInfo(userId);
    }
}
