package com.ddm.model;

import lombok.*;

/**
 * Created by yunpeng.song on 6/14/2018.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCondition {
    private String userName;

    private String password;

    private String phone;
}
