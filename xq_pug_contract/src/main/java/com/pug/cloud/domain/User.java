package com.pug.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements java.io.Serializable{
    private Long userId;
    private String username;
    private String password;
    private String address;
    private String role;
}
