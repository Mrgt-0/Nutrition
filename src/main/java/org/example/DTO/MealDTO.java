package org.example.DTO;

import org.example.Model.Dish;
import org.example.Model.User;
import java.util.HashSet;
import java.util.Set;

public class MealDTO {
    private Long id;
    private String mealName;
    private Set<Dish> dishes = new HashSet<>();
    private User user;

    public MealDTO(String mealName, Set<Dish> dishes, User user) {
        this.mealName = mealName;
        this.dishes = dishes;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMealName() { return mealName; }
    public void setMealName(String mealName) { this.mealName = mealName; }

    public Set<Dish> getDishes() { return dishes; }
    public void setDishes(Set<Dish> dishes) { this.dishes = dishes; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
