package com.springExampleApp.repositories;

import com.springExampleApp.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
       Page<UserEntity> findAllByBirthDate(Pageable pageable, String date);
}
