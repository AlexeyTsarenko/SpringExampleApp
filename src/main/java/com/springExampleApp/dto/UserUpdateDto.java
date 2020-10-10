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
    private int id;

    private String email;

    private Integer password;

    private Date birthDate;

    private String firstName;

    private String lastName;
}
