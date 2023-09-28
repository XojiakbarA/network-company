package uz.pdp.networkcompany.enums;

import lombok.Getter;

@Getter
public enum DurationType {
    DAY(1), THREE_DAY(3), WEEK(7), MONTH(30);

    private final int value;

    DurationType(int value) {
        this.value = value;
    }
}
