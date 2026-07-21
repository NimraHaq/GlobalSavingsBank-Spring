package com.vault.dto;

import com.vault.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//this class could have been a record

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CardDto {
    private long cardSrno;
    private long cardNo;
    private String category;
    private CustomerDto customerDto;
    private long primaryCardNo;
    private String cardStatus = Constants.CARD_PRE_ACTIVE_STATUS;
    private String isMainCard= Constants.OPTION_YES;
    private String isActive= Constants.OPTION_YES;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
}
