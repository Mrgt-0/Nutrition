package org.example.DTO;

import java.util.List;

public class DailyReportDTO {
    private double totalCalories;
    private boolean withinLimit;
    private List<MealDTO> meals;

    public DailyReportDTO(double totalCalories, boolean withinLimit, List<MealDTO> meals) {
        this.totalCalories = totalCalories;
        this.withinLimit = withinLimit;
        this.meals = meals;
    }

    public double getTotalCalories() { return totalCalories; }
    public void setTotalCalories(double totalCalories) { this.totalCalories = totalCalories; }

    public boolean isWithinLimit() { return withinLimit; }
    public void setWithinLimit(boolean withinLimit) { this.withinLimit = withinLimit; }

    public List<MealDTO> getMeals() { return meals; }
    public void setMeals(List<MealDTO> meals) { this.meals = meals; }
}
