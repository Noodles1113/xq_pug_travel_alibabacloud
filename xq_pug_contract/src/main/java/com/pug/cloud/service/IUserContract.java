package com.pug.cloud.service;

import com.pug.cloud.domain.User;

public interface IUserContract {


    User saveUser(User user);


    User getUserInfo(Long userId);


}
