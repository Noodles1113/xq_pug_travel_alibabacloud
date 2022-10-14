package com.pug.cloud.service;

import com.pug.cloud.domain.User;

import java.util.List;

public interface IUserContract {


    User saveUser(User user);


    User getUserInfo(Long userId);

    List<User> getUserInfoList(User user);


}
