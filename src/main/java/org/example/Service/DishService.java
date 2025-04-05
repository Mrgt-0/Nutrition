package org.example.Service;

import org.example.DTO.DishDTO;
import org.example.Mapper.DishMapper;
import org.example.Model.Dish;
import org.example.Repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishMapper dishMapper;

    public void createDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        dish.setName(dishDTO.getName());
        dish.setCalories(dishDTO.getCalories());
        dish.setProtein(dishDTO.getProtein());
        dish.setFats(dishDTO.getFats());
        dish.setCarbohydrates(dishDTO.getCarbohydrates());

        dishRepository.save(dish);
    }

    public List<DishDTO> findDishesByNames(List<String> dishNames) {
        List<DishDTO> dishes = new ArrayList<>();
        for (String name : dishNames) {
            List<Dish> foundDishes = dishRepository.findByName(name);
            for (Dish dish : foundDishes) {
                dishes.add(dishMapper.toDTO(dish));
            }
        }
        return dishes;
    }
}
