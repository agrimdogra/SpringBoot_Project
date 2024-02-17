package com.ecomerce.user_new.dto.responseDto;

import com.ecomerce.user_new.enums.SessionStatus;
import com.ecomerce.user_new.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionResponseDto {
    private String token;
    private LocalDateTime expiringAt;
    private UserResponseDto userResponseDto;
    private SessionStatus sessionStatus;
}
