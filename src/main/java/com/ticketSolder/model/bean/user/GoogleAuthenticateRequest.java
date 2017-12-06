package com.ticketSolder.model.bean.user;

import lombok.Data;

/**
 * Created by ss on 2017/12/4.
 */

@Data
public class GoogleAuthenticateRequest {

    private String userName;
    private String password;
    private String email;
}
