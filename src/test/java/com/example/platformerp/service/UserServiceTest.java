package com.example.platformerp.service;

import com.example.platformerp.dto.JwtResponseDto;
import com.example.platformerp.dto.LoginDto;
import com.example.platformerp.dto.user.UserCreatedDto;
import com.example.platformerp.jwt.JwtService;
import com.example.platformerp.model.UserEntity;
import com.example.platformerp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ModelMapper mapper;
    @Test
    void register() {
        UserCreatedDto user = new UserCreatedDto(
                "Elyor",
                "Azimov",
                "ElyorAzimov",
                new HashSet<>(),
                "998991234567",
                "email@gmail.com",
                "1234qwer",
                "1234qwer"

        );
        UserEntity userEntity = new UserEntity(user.getFirstName(),user.getLastName(),user.getMiddleName(),
                user.getPhoneNumber(),user.getEmail(), user.getPassword(), new ArrayList<>(), null,true, true);
        userEntity.setId(UUID.randomUUID());
        JwtResponseDto response = new JwtResponseDto("Hello");
        when(mapper.map(user, UserEntity.class)).thenReturn(userEntity);
        when(jwtService.generateToken(userEntity)).thenReturn("Hello");
        when(passwordEncoder.encode(user.getPassword())).thenReturn("blabla");
        when(userService.register(user)).thenReturn(new JwtResponseDto("hello"));
        JwtResponseDto register = userService.register(user);
        Assertions.assertNotNull(register);
        Assertions.assertEquals(response.getToken(),register.getToken());

    }

    @Test
    void login() {
        userRepository.save(UserEntity.builder()
                .firstName("Elyor")
                        .lastName("Azimov")
                        .email("elyor@gmail.com")
                        .password("1234rtyu")
                .build());
        LoginDto login = new LoginDto("elyor@gmail.com","1234rtyu");
        JwtResponseDto response = new JwtResponseDto("Hello");
        when(userService.login(login)).thenReturn(response);
        JwtResponseDto token = userService.login(login);
        assertNotNull(token);
    }
}