package com.example.platformerp.dto.responseData;

import com.example.platformerp.utils.RestConstants;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO {

    private final String tokenType = RestConstants.TOKEN_TYPE;

    private String accessToken;

    private String refreshToken;
}
