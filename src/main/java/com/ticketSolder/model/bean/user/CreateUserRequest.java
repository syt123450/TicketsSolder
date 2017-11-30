package com.ticketSolder.model.bean.user;

import lombok.Data;

/**
 * Created by ss on 2017/11/29.
 */

@Data
public class CreateUserRequest {

    private String userName;
    private String password;
    private String email;
}
