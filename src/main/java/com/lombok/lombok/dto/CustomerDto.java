package com.lombok.lombok.dto;

import com.lombok.lombok.utils.Constants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class CustomerDto {
    private int chId;


    private Long defaultCardSrno = null;

    private int registeredCards = 0;

    @Builder.Default
    private String isActive = Constants.IS_ACTIVE;

    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
}
