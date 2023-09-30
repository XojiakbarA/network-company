package uz.pdp.networkcompany.enums;

import lombok.Getter;

@Getter
public enum USSDType {
    BALANCE("*101#"), TARIFF("*102#"), MINUTE("*103#"), MB("*104#"), SMS("*105#");

    private final String code;

    USSDType(String code) {
        this.code = code;
    }
}
