package com.lombok.lombok.dto;

import com.lombok.lombok.utils.Constants;
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
    private int chId;
    private long primaryCardNo;
    private String cardStatus = Constants.CARD_PRE_ACTIVE_STATUS;
    private String isMainCard= Constants.IS_ACTIVE;
    private String isActive= Constants.IS_ACTIVE;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
}
