package com.example.platformerp.service;

import com.example.platformerp.controller.AuthController;
import com.example.platformerp.dto.JwtResponseDto;
import com.example.platformerp.dto.LoginDto;
import com.example.platformerp.dto.user.UserCreatedDto;
import com.example.platformerp.exceptions.DataNotFoundException;
import com.example.platformerp.jwt.JwtService;
import com.example.platformerp.model.UserEntity;
import com.example.platformerp.model.UserRole;
import com.example.platformerp.repository.UserRepository;
import com.example.platformerp.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository roleRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    public JwtResponseDto register(UserCreatedDto user) {
        LOGGER.info("new User -> " + user.toString());
        if(!user.getPassword().equals(user.getConfirmPassword())){
            throw new RuntimeException("Incorrect confirm password");
        }
        if (userRepository.existsByEmailOrPhoneNumber(user.getEmail(),user.getPhoneNumber())) {
            throw new RuntimeException("Email or phone number already exists");
        }
        List<UserRole> userRoles = getUserRoleInDto(user.getRoles());
        UserEntity mapUser = modelMapper.map(user, UserEntity.class);
        mapUser.setPassword(passwordEncoder.encode(user.getPassword()));
        mapUser.setRoles(userRoles);
        userRepository.save(mapUser);
        String token = jwtService.generateToken(mapUser);
        LOGGER.info("User successfully saved");
        return JwtResponseDto.builder().token(token).build();
    }

    public JwtResponseDto login(LoginDto loginDto) {
        UserEntity user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            throw new UsernameNotFoundException("User not found");
        }
        String token = jwtService.generateToken(user);
        LOGGER.info("User successfully login");
        return JwtResponseDto.builder().token(token).build();
    }
    private List<UserRole> getUserRoleInDto(Set<String> roles){
        List<UserRole> userRoles = roleRepository.findByNameIn(roles);
        if(userRoles.size()!=roles.size()){
            throw new DataNotFoundException("Role not found");
        }
        return userRoles;
    }
}
