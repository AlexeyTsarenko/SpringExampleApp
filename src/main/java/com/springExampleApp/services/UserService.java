package com.springExampleApp.services;

import com.springExampleApp.dto.UserUpdateDto;
import com.springExampleApp.entities.HistoryEntity;
import com.springExampleApp.entities.UserEntity;
import com.springExampleApp.dto.UserSaveDto;
import com.springExampleApp.repositories.HistoryRepository;
import com.springExampleApp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    UserService(UserRepository userRepository, ModelMapper modelMapper, HistoryRepository historyRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.historyRepository = historyRepository;
    }

    public UserEntity save(UserSaveDto userSaveDto) {
        UserEntity userEntity = modelMapper.map(userSaveDto, UserEntity.class);
        userEntity.setStatus("active");
        return userRepository.save(userEntity);
    }

    public ResponseEntity<String> update(UserUpdateDto userUpdateDto) {
        Optional<UserEntity> optional = userRepository.findById(userUpdateDto.getId());
        if (optional.isPresent()&&!(optional.get().getStatus().equals("deleted"))) {
            UserEntity userEntity = optional.get();
            return createHistory(userUpdateDto, userEntity);
        } else {
            return new ResponseEntity<>("No record with such id", HttpStatus.NOT_FOUND);
        }

    }
    public ResponseEntity<String> delete(int id) {
        Optional<UserEntity> optional = userRepository.findById(id);
        if(optional.isPresent()){
            UserEntity userEntity = optional.get();
            userEntity.setStatus("deleted");
            userRepository.save(userEntity);
            return new ResponseEntity<>(userEntity.getId().toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No record with such id", HttpStatus.NOT_FOUND);
    }
    public Page<UserEntity> getByBirthDate(String date, Pageable pageableRequest) {
        return userRepository.findAllByBirthDate(pageableRequest, date);
    }

    private ResponseEntity<String> createHistory(UserUpdateDto userUpdateDto, UserEntity userEntity) {
        boolean nothingToUpdate = true;
        HistoryEntity historyEntity;
        List<HistoryEntity> historyEntityList = new ArrayList<>();
        if (!(userEntity.getBirthDate().equals(userUpdateDto.getBirthDate()))&&userUpdateDto.getBirthDate()!=null) {
            historyEntity = new HistoryEntity();
            historyEntity.setFieldName("age");
            historyEntity.setOldValue(userEntity.getBirthDate().toString());
            historyEntity.setNewValue(userUpdateDto.getBirthDate().toString());
            userEntity.setBirthDate(userUpdateDto.getBirthDate());
            historyEntityList.add(historyEntity);

            nothingToUpdate = false;
        }
        if (!(userEntity.getEmail().equals(userUpdateDto.getEmail()))&&userUpdateDto.getEmail()!=null) {
            historyEntity = new HistoryEntity();
            historyEntity.setFieldName("email");
            historyEntity.setOldValue(userEntity.getEmail());
            historyEntity.setNewValue(userUpdateDto.getEmail());
            userEntity.setEmail(userUpdateDto.getEmail());
            historyEntityList.add(historyEntity);

            nothingToUpdate = false;
        }
        if (!(userEntity.getPassword().equals(userUpdateDto.getPassword()))&&userUpdateDto.getPassword()!=null) {
            historyEntity = new HistoryEntity();
            historyEntity.setFieldName("password");
            historyEntity.setOldValue(userEntity.getPassword().toString());
            historyEntity.setNewValue(userUpdateDto.getPassword().toString());
            userEntity.setPassword(userUpdateDto.getPassword());
            historyEntityList.add(historyEntity);

            nothingToUpdate = false;
        }
        if (!(userEntity.getFirstName().equals(userUpdateDto.getFirstName()))&&userUpdateDto.getFirstName()!=null) {
            historyEntity = new HistoryEntity();
            historyEntity.setFieldName("date");
            historyEntity.setOldValue(userEntity.getFirstName());
            historyEntity.setNewValue(userUpdateDto.getFirstName());
            userEntity.setFirstName(userUpdateDto.getFirstName());
            historyEntityList.add(historyEntity);
            nothingToUpdate = false;
        }
        if(nothingToUpdate){
            return new ResponseEntity<>("Nothing to update", HttpStatus.OK);
        }
        userEntity.setStatus("updated");
        historyEntityList.forEach(x -> x.setEntityId(userEntity.getId()));
        historyEntityList.forEach(historyRepository::save);
        userRepository.save(userEntity);
        return new ResponseEntity<>(userEntity.getId().toString(), HttpStatus.OK);
    }

}
