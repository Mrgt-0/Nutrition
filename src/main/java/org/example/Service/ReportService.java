package org.example.Service;

import org.example.DTO.DailyCalorieAndMacroDTO;
import org.example.DTO.DailyReportDTO;
import org.example.DTO.MealDTO;
import org.example.Mapper.UserMapper;
import org.example.Model.Meal;
import org.example.Model.User;
import org.example.Repository.MealRepository;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private UserMapper userMapper;

    public DailyReportDTO generateDailyReport(String email, LocalDate date) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new IllegalArgumentException("Пользователь не найден");

        List<Meal> meals = mealRepository.findByUserAndDate(user, date);

        double totalCalories = meals.stream().mapToDouble(Meal::getTotalCalories).sum();
        boolean withinLimit = totalCalories <= calculatorService.getDailyCalorieLimit(userMapper.toDTO(user));

        List<MealDTO> mealDTOs = meals.stream()
                .map(meal -> new MealDTO(meal.getMealName(), meal.getDishes(), user))
                .collect(Collectors.toList());

        return new DailyReportDTO(totalCalories, withinLimit, mealDTOs);
    }
}
