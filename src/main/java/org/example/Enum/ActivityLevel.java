package org.example.Enum;

public enum ActivityLevel {
    SEDENTARY(1.2),
    LIGHT(1.375),
    MODERATE(1.55),
    ACTIVE(1.725),
    VERY_ACTIVE(1.9);

    private final double level;

    ActivityLevel(double level) {
        this.level = level;
    }

    public double getLevel() {
        return level;
    }
}
