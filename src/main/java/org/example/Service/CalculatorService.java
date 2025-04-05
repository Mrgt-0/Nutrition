package org.example.Service;

import org.example.DTO.DailyCalorieAndMacroDTO;
import org.example.DTO.UserDTO;
import org.example.Enum.Gender;
import org.example.Enum.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    public DailyCalorieAndMacroDTO calculateDailyCaloriesAndMacros(UserDTO userDTO) {
        double calorieNorm = calculateBMR(userDTO) * userDTO.getActivityLevel();
        calorieNorm = adjustCaloriesForTarget(calorieNorm, userDTO.getTarget());

        int totalCalories = (int) Math.round(calorieNorm);

        return calculateMacros(totalCalories, userDTO.getTarget());
    }

    public double getDailyCalorieLimit(UserDTO userDTO) {
        return calculateBMR(userDTO) * userDTO.getActivityLevel();
    }

    private double calculateBMR(UserDTO userDTO) {
        double BMR;
        if (userDTO.getGender() == Gender.MALE) {
            BMR = 88.362 + (13.397 * userDTO.getWeight()) + (4.799 * userDTO.getHeight()) - (5.677 * userDTO.getAge());
        } else if (userDTO.getGender() == Gender.FEMALE) {
            BMR = 447.593 + (9.247 * userDTO.getWeight()) + (3.098 * userDTO.getHeight()) - (4.330 * userDTO.getAge());
        } else {
            logger.error("Поле пол не заполнено, установка калорийной нормы в 0.");
            return 0;
        }
        return BMR;
    }

    private double adjustCaloriesForTarget(double calorieNorm, Target target) {
        switch (target) {
            case LOSE_WEIGHT:
                return calorieNorm - 500;
            case GAIN_WEIGHT:
                return calorieNorm + 250;
            case MAINTAIN_WEIGHT:
                return calorieNorm;
            default:
                logger.warn("Неизвестная цель: " + target);
                return calorieNorm;
        }
    }

    private DailyCalorieAndMacroDTO calculateMacros(int totalCalories, Target target) {
        double protein, fat, carbs;
        switch (target) {
            case LOSE_WEIGHT:
                protein = totalCalories * 0.30 / 4;
                fat = totalCalories * 0.30 / 9;
                carbs = totalCalories * 0.40 / 4;
                break;
            case GAIN_WEIGHT:
                protein = totalCalories * 0.25 / 4;
                fat = totalCalories * 0.25 / 9;
                carbs = totalCalories * 0.50 / 4;
                break;
            case MAINTAIN_WEIGHT:
                protein = totalCalories * 0.30 / 4;
                fat = totalCalories * 0.25 / 9;
                carbs = totalCalories * 0.45 / 4;
                break;
            default:
                logger.warn("Не удалось рассчитать БЖУ из-за неизвестной цели.");
                protein = fat = carbs = 0;
                break;
        }
        return new DailyCalorieAndMacroDTO(
                totalCalories,
                (int) Math.round(protein),
                (int) Math.round(fat),
                (int) Math.round(carbs)
        );
    }

    public double bodyMassIndexCalculation(UserDTO userDTO) {
        double heightInMeters = userDTO.getHeight() / 100.0;
        return (double) Math.round(userDTO.getWeight() / Math.pow(heightInMeters, 2) * 100.0) / 100.0;
    }

    public String interpretationOfBodyMassIndex(double bodyMassIndex) {
        if (bodyMassIndex <= 16)
            return "Выраженный дефицит массы тела";
        else if(bodyMassIndex <= 18.5)
            return "Недостаточная (дефицит) масса тела";
        else if(bodyMassIndex <= 25)
            return "Норма";
        else if(bodyMassIndex <= 30)
            return "Избыточная масса тела (предожирение)";
        else if(bodyMassIndex <= 35)
            return "Ожирение 1 степени";
        else if(bodyMassIndex <= 40)
            return "Ожирение 2 степени";
        else return "Ожирение 3 степени";
    }

    public int calculationOfIdealWeight(UserDTO userDTO) {
        double idealWeight;
        if (userDTO.getGender() == Gender.MALE)
            idealWeight = (userDTO.getHeight() - 100) * 1.15;
        else if (userDTO.getGender() == Gender.FEMALE)
            idealWeight = (userDTO.getHeight() - 110) * 1.15;
        else {
            logger.error("Поле пол не заполнено, установка калорийной нормы в 0.");
            return 0;
        }
        return (int) Math.round(idealWeight);
    }
}
