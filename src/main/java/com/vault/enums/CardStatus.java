package com.vault.enums;

public enum CardStatus {
    PRE_ACTIVE("A"),
    ACTIVE("B"),
    BLOCKED("F"),
    EXPIRED("E");

    private String status;

    CardStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

}
