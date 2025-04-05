package org.example.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meal_name", nullable = false)
    private String mealName;

    @ManyToMany
    @JoinTable(
            name = "meal_dish",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private Set<Dish> dishes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "meal_date", nullable = false)
    private LocalDate date;

    public double getTotalCalories() {
        return dishes.stream()
                .mapToDouble(Dish::getCalories)
                .sum();
    }

    public Meal() {}

    public Meal(String mealName, Set<Dish> dishes,  User user, LocalDate date) {
        this.mealName = mealName;
        this.dishes = dishes;
        this.user = user;
        this.date = date;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMealName() { return mealName; }
    public void setMealName(String mealName) { this.mealName = mealName; }

    public Set<Dish> getDishes() { return dishes; }
    public void setDishes(Set<Dish> dishes) { this.dishes = dishes; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
