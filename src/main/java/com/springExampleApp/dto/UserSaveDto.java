package com.springExampleApp.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveDto {

    private String email;

    private Integer password;

    private Date birthDate;

    private String firstName;

    private String lastName;
}
