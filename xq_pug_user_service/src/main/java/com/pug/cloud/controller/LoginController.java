package com.pug.cloud.controller;

import com.pug.cloud.domain.User;
import com.pug.dto.login.JwtTokenDto;
import com.pug.dto.login.LoginResponseDto;
import com.pug.dto.login.UserResponseDto;
import com.pug.cloud.service.JwtOperatorService;
import com.pug.vo.login.UserLoginVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class LoginController {

    private final JwtOperatorService jwtOperatorService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody UserLoginVo userLoginVo) {
        User user = new User();
        user.setUserId(10000L);
        user.setUsername(userLoginVo.getNickname());
        user.setPassword(userLoginVo.getPassword());
        user.setAddress("湖南长沙");
        user.setRole("admin");
        // 创建token
        String token = jwtOperatorService.generateToken(user);
        log.info("用户生成token成功，{} ，有效期是：{}", token, jwtOperatorService.getExpirationTime());
        // 返回参数
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        // 用户信息
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getUserId());
        userResponseDto.setAvatarUrl("1.jpg");
        userResponseDto.setLevel("1");
        userResponseDto.setUsername(user.getUsername());
        // jwt信息
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setToken(token);
        jwtTokenDto.setExpireTime(jwtTokenDto.getExpireTime());

        loginResponseDto.setUser(userResponseDto);
        loginResponseDto.setToken(jwtTokenDto);
        return loginResponseDto;
    }
}
