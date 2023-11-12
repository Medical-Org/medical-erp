package com.example.platformerp.repository;

import com.example.platformerp.model.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private final String testEmail = "elyor@gmail.com";
    @BeforeEach
    public void addUser(){
        UserEntity build = UserEntity.builder()
                .firstName("Elyor")
                .lastName("Azimov")
                .middleName("bla")
                .phoneNumber("+998994724134")
                .email(testEmail)
                .build();
        userRepository.save(build);
    }
    @Test
    void findByEmail() {
        Optional<UserEntity> byEmail = userRepository.findByEmail(testEmail);
        Assertions.assertTrue(byEmail.isPresent());
    }

    @Test
    void existsByEmail() {
        Assertions.assertTrue(userRepository.existsByEmail(testEmail));
    }
}