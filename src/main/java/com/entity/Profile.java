package com.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Profile {
    private long id;
    private String login;
    private String password;
}
