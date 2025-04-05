package org.example.DTO;
import jakarta.validation.constraints.NotEmpty;

public class DishDTO {
    private String id;

    @NotEmpty(message = "Поле название не должно быть пустым")
    private String name;

    @NotEmpty(message = "Поле калории не должно быть пустым")
    private int calories;

    @NotEmpty(message = "Поле белки не должно быть пустым")
    private double protein;

    @NotEmpty(message = "Поле жиры не должно быть пустым")
    private double fats;

    @NotEmpty(message = "Поле углеводы не должно быть пустым")
    private double carbohydrates;

    public DishDTO(String name, int calories, double protein, double fats, double carbohydrates) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }

    public double getProtein() { return protein; }
    public void setProtein(double protein) { this.protein = protein; }

    public double getFats() { return fats; }
    public void setFats(double fats) { this.fats = fats; }

    public double getCarbohydrates() { return carbohydrates; }
    public void setCarbohydrates(double carbohydrates) { this.carbohydrates = carbohydrates; }
}
