package com.ecomerce.user_new.model;

import com.ecomerce.user_new.dto.responseDto.SessionResponseDto;
import com.ecomerce.user_new.enums.SessionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Session extends BaseModel{
    private String token;
    private LocalDateTime expiringAt;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;

    public SessionResponseDto toSessionResponseDto(){
        return SessionResponseDto.builder()
                .token(this.token)
                .expiringAt(this.expiringAt)
                .userResponseDto(user.toUserResponse())
                .sessionStatus(this.sessionStatus)
                .build();
    }
}
