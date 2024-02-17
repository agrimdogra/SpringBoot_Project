package com.ecomerce.user_new.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public enum SessionStatus {
    ACTIVE,
    INACTIVE,
    EXPIRED;
}
