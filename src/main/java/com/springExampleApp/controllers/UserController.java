package com.springExampleApp.controllers;

import com.springExampleApp.dto.UserUpdateDto;
import com.springExampleApp.entities.UserEntity;
import com.springExampleApp.dto.UserSaveDto;
import com.springExampleApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody UserSaveDto userSaveDto){
        UserEntity simpleEntity =  userService.save(userSaveDto);
        return new ResponseEntity<>(simpleEntity.getId().toString(), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserUpdateDto userUpdateDto){
        return userService.update(userUpdateDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        return userService.delete(id);
    }

    @GetMapping(path = "/getByDate/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserEntity> getEntities(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "5") int limit,
                                        @PathVariable String date) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        return userService.getByBirthDate(date, pageableRequest);
    }
}
