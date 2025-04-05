package org.example.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Meal> meals;

    private int numbersOfMeals;

    private double totalCalories;

    public Report(List<Meal> meals, int numbersOfMeals, double totalCalories) {
        this.meals = meals;
        this.numbersOfMeals = numbersOfMeals;
        this.totalCalories = totalCalories;

    }
    public Report() {

    }
}
