package com.vault.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private Long transId;
    private Long cardNo;
    private String serviceId;
    private BigDecimal serviceCharges = BigDecimal.ZERO;
    private BigDecimal amountProcessed = BigDecimal.ZERO;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
}