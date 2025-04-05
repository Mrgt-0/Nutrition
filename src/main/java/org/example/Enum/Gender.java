package org.example.Enum;

public enum Gender {
    MALE("мужской"),
    FEMALE("женский");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Gender fromValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.getValue().equalsIgnoreCase(value.trim())) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Нет такого значения: " + value);
    }
}
