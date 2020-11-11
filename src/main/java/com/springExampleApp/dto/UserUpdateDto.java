package com.springExampleApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    private long id;

    private String email;

    private String password;

    private Date birthDate;

    private String firstName;

    private String lastName;
}
