package org.example.Service;

import org.example.Model.Dish;
import org.example.Model.Meal;
import org.example.Model.User;
import org.example.Repository.DishRepository;
import org.example.Repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class MealService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private MealRepository mealRepository;

    public Meal createMeal(String mealName, List<String> dishNames, LocalDate date, User user) {
        Meal meal = new Meal();
        meal.setMealName(mealName);
        meal.setDate(date);
        meal.setUser(user);

        List<Dish> dishes = new ArrayList<>();
        for (String name : dishNames) {
            List<Dish> foundDishes = dishRepository.findByName(name);
            dishes.addAll(foundDishes);
        }
        meal.setDishes(new HashSet<>(dishes));

        return mealRepository.save(meal);
    }
}
