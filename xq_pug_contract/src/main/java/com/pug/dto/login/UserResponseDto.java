package com.pug.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022-06-25$ 15:25$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    // 用户ID
    private Long userId;
    // 头像
    private String avatarUrl;
    // 用户级别
    private String level;
    // 用户名称
    private String username;
}
