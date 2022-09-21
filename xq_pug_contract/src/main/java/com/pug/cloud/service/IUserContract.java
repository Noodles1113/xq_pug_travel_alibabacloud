package com.pug.cloud.service;

import com.pug.cloud.domain.User;

public interface IUserContract {


    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    User saveUser(User user);


    /**
     * 根据用户ID查询用户信息
     *
     * @param userId
     * @return
     */
    User getUserInfo(Long userId);


}
