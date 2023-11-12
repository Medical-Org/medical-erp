package com.example.platformerp.utils;

import com.example.platformerp.controller.AuthController;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface RestConstants {

    String[] OPEN_PAGES = {
            AuthController.BASE_PATH + "/**"
    };

    String BASE_PATH = "/api";
    String BASE_PATH_V1 = BASE_PATH + "/v1";

    String AUTHENTICATION_HEADER = "Authorization";
    ObjectMapper objectMapper = new ObjectMapper();

    String TOKEN_TYPE = "Bearer";


    int INCORRECT_USERNAME_OR_PASSWORD = 3001;

    int REQUIRED = 3007;

    int SERVER_ERROR = 3008;
    int CONFLICT = 3009;
    int NO_ITEMS_FOUND = 3011;
    int CONFIRMATION = 3012;
    int USER_NOT_ACTIVE = 3013;
    int JWT_TOKEN_INVALID = 3014;

    String VERIFICATION_EMAIL_SUBJECT = "Verify Your Email";
    String PASSWORD_RESET_EMAIL_SUBJECT = "Password Reset Request";
    String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    String UZB_PHONE_NUMBER_REGEX = """
            ^(\\+998)?\\s?\\(?\\d{2}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$""";
    String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    String NUMBER_REGEX = "^\\d+$";
    String NUMBER_GREATER = "NUMBER_GREATER";
    String ONE_OF_LIST = "ONE_OF_LIST";
    String USER_ENTERED = "USER_ENTERED";

    String DEFAULT_PAGE = "0";
    String DEFAULT_PAGE_SIZE_FOR_SEARCH = "5";
    String DEFAULT_PAGE_SIZE = "10";
}
