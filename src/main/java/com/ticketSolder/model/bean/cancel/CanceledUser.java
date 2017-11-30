package com.ticketSolder.model.bean.cancel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ss on 2017/11/27.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CanceledUser {

    private String userName;
    private String email;
}