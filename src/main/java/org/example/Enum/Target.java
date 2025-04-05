package org.example.Enum;

public enum Target {
    LOSE_WEIGHT("похудеть"),
    GAIN_WEIGHT("набирать вес"),
    MAINTAIN_WEIGHT("поддерживать вес");

    private final String value;

    Target(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Target fromValue(String value) {
        for (Target target : Target.values()) {
            if (target.getValue().equalsIgnoreCase(value.trim())) {
                return target;
            }
        }
        throw new IllegalArgumentException("Нет такого значения: " + value);
    }
}
