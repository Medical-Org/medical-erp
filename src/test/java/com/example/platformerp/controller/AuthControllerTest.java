package com.example.platformerp.controller;

import com.example.platformerp.dto.JwtResponseDto;
import com.example.platformerp.dto.LoginDto;
import com.example.platformerp.dto.user.UserCreatedDto;
import com.example.platformerp.jwt.JwtService;
import com.example.platformerp.repository.UserRepository;
import com.example.platformerp.repository.UserRoleRepository;
import com.example.platformerp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private UserRepository userRepository;
    @Mock
    private ModelMapper  modelMapper;
    @Mock
    private UserRoleRepository roleRepository;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbHlvcjExQGdtYWlsLmNvbSIsImlhdCI6MTY5OTA3NzIzMSwiZXhwIjoxNjk5MDg" +
                "wODMxLCJyb2xlcyI6WyJST0xFU1RVREVOVCJdfQ.sy-De6L9MZPZQ-z1xdqc8QbqCLwXqlIDZCmNc8j_3H5SJwaP7bjkrElYXObm9U0EU-tq3uPYpFKW6OLEWLKx4A";
        UserCreatedDto user = new UserCreatedDto(
                "Elyor",
                "Azimov",
                "ElyorAzimov",
                new HashSet<>(),
                "998994724134",
                "elyor@gmail.com",
                "ea1202",
                "ea1202"
        );
        when(userService.register(user)).thenReturn(JwtResponseDto.builder().token(token).build());
    }

    @Test
    void signUp() throws Exception {
        UserCreatedDto userCreatedDto = new UserCreatedDto(
                "Elyor",
                "Azimov",
                "ElyorAzimov",
                new HashSet<>(),
                "998994724132",
                "elyor1@gmail.com",
                "1234rtyu",
                "1234rtyu"
        );
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userCreatedDto))
        );
        perform.andExpect(status().isCreated());

    }
    @Test
    void validTest() throws Exception {
        UserCreatedDto userCreatedDto = new UserCreatedDto(
                "Elyor",
                "Azimov",
                "ElyorAzimov",
                new HashSet<>(),
                "+998994724132",
                "elyor1@gmail.com",
                "1234rtyu",
                "1234rtyu"
        );
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userCreatedDto))
        );
        perform.andExpect(status().is(400));
        int status = perform.andReturn().getResponse().getStatus();
        Assertions.assertEquals(status,400);


    }
    @Test
    public void signIn() throws Exception {
        LoginDto loginDto = new LoginDto("elyor1@gmail.com", "1234rtyu  ");
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto))
        );
        perform.andExpect(status().isOk());
    }
}