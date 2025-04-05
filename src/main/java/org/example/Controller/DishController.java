package org.example.Controller;

import jakarta.validation.Valid;
import org.example.DTO.DishDTO;
import org.example.Model.Dish;
import org.example.Service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addDish")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<?> addDish(@Valid @RequestParam String name,
                                     @RequestParam int calories,
                                     @RequestParam double protein,
                                     @RequestParam double fats,
                                     @RequestParam double carbohydrates) {
        DishDTO dish = new DishDTO(name, calories, protein, fats, carbohydrates);
        dishService.createDish(dish);
        return ResponseEntity.ok("Блюдо успешно добавлено.");
    }
}
