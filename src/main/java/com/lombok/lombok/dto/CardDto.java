package com.lombok.lombok.dto;

import com.lombok.lombok.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//this class could have been a record

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CardDto {
    private long cardSrno;
    private long cardNo;
    private String category;
    private int chId;
    private long primaryCardNo;
    private String isMainCard= Constants.IS_ACTIVE;
    private String isActive= Constants.IS_ACTIVE;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;

    // Private constructor to enforce use of Builder
    private CardDto(Builder builder) {
        this.cardSrno = builder.cardSrno;
        this.cardNo = builder.cardNo;
        this.category = builder.category;
        this.chId = builder.chId;
        this.primaryCardNo = builder.primaryCardNo;
        this.isMainCard = builder.isMainCard;
        this.isActive = builder.isActive;
        this.createdOn = builder.createdOn;
        this.lastUpdatedOn = builder.lastUpdatedOn;
    }

    // Static inner Builder class
    public static class Builder {
        private long cardSrno;
        private long cardNo;
        private String category;
        private int chId;
        private long primaryCardNo;
        private String isMainCard = Constants.IS_ACTIVE;
        private String isActive = Constants.IS_ACTIVE;
        private LocalDateTime createdOn;
        private LocalDateTime lastUpdatedOn;

        public Builder cardSrno(long cardSrno) {
            this.cardSrno = cardSrno;
            return this;
        }

        public Builder cardNo(long cardNo) {
            this.cardNo = cardNo;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder chId(int ch_id) {
            this.chId = ch_id;
            return this;
        }

        public Builder primaryCardNo(long primaryCardNo) {
            this.primaryCardNo = primaryCardNo;
            return this;
        }

        public Builder isMainCard(String isMainCard) {
            this.isMainCard = isMainCard;
            return this;
        }

        public Builder isActive(String isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder createdOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public Builder lastUpdatedOn(LocalDateTime lastUpdatedOn) {
            this.lastUpdatedOn = lastUpdatedOn;
            return this;
        }

        public CardDto build() {
            return new CardDto(this);
        }
    }
}
