package com.vault.dto;

import com.vault.utils.Constants;
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
    private String isActive = Constants.OPTION_YES;

    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
}
