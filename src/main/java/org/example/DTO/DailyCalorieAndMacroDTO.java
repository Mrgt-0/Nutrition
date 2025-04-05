package org.example.DTO;

public class DailyCalorieAndMacroDTO {
    private int totalCalories;
    private int protein;
    private int fat;
    private int carbohydrates;

    public DailyCalorieAndMacroDTO(int totalCalories, int protein, int fat, int carbs) {
        this.totalCalories = totalCalories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbs;
    }

    public int getTotalCalories() { return totalCalories; }
    public int getProtein() { return protein; }

    public int getFat() { return fat; }
    public int getCarbs() { return carbohydrates; }
}
