package com.vault.enums;

public enum ServiceIds {
    ADD_CARD("ADD_CARD");

    String serviceId;

    ServiceIds(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }
}
