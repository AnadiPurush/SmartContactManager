package com.utsavsharma.smartContactManager.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserForm.java
 * Created by utsav on 03-Aug-2024 at 10:00:58 am.
 */

@Data

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

    private String name;

    private String email;

    private String phone;

    private String password;

    private String about;

}
