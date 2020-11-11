package com.springExampleApp.repositories;

import com.springExampleApp.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
       Page<UserEntity> findAllByBirthDate(Pageable pageable, String date);
       Optional<UserEntity> findByEmail(String email);
}
