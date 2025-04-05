package org.example.Controller;

import org.example.Config.MyUserDetails;
import org.example.DTO.DishDTO;
import org.example.Mapper.UserMapper;
import org.example.Model.Report;
import org.example.Service.DishService;
import org.example.Service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/addMeals")
public class MealController {
    @Autowired
    private MealService mealService;

    @Autowired
    private DishService dishService;

    private static final Logger logger = LoggerFactory.getLogger(MealController.class);
    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public ResponseEntity<?> addMeal(@RequestParam String mealName, @RequestParam List<String> dishNames, Authentication authentication) {
        if (mealName == null || mealName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Имя приема пищи не может быть пустым.");
        }
        if (dishNames == null || dishNames.isEmpty()) {
            return ResponseEntity.badRequest().body("Список блюд не может быть пустым.");
        }

        List<DishDTO> dishesDTO = dishService.findDishesByNames(dishNames);
        if (dishesDTO.isEmpty()) {
            return ResponseEntity.badRequest().body("Не найдены блюда с таким названием.");
        }
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        mealService.createMeal(mealName, dishNames, LocalDate.now(), userMapper.toEntity(userDetails.getUser()));
        logger.info("Прием пищи '{}' успешно добавлен.", mealName);
        return ResponseEntity.ok("Прием пищи успешно добавлен.");
    }
}
