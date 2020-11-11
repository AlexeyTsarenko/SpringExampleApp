package com.springExampleApp.services;

import com.springExampleApp.dto.UserSaveDto;
import com.springExampleApp.dto.UserUpdateDto;
import com.springExampleApp.entities.UserEntity;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserEntity save(UserSaveDto userSaveDto);
    UserEntity findById(Long id);
    ResponseEntity<String> update(UserUpdateDto userUpdateDto);
    ResponseEntity<String> delete(Long id);
    UserEntity findByEmail(String email);

}
