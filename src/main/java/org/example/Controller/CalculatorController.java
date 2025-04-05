package org.example.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.Config.MyUserDetails;
import org.example.DTO.DailyCalorieAndMacroDTO;
import org.example.Service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping
    public ResponseEntity<?> getBodyAnalysisResults(Authentication authentication){
        logger.info("Authentication Status: {}", authentication);
        if (authentication == null || !authentication.isAuthenticated()) {
            logger.error("Неудачная аутентификация для пользователя. Аутентификация не была установлена.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неудачная аутентификация: доступ запрещен. Пожалуйста, выполните вход.");
        }

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        logger.info("Пользователь: {}", userDetails.getUsername());

        DailyCalorieAndMacroDTO result = calculatorService.calculateDailyCaloriesAndMacros(userDetails.getUser());
        logger.info("Ваша дневная норма калорий: {}", result.getTotalCalories());

        double calculationBMI = calculatorService.bodyMassIndexCalculation(userDetails.getUser());
        logger.info("Индекс массы тела: {}", calculationBMI);

        String bmiInterpretation  = calculatorService.interpretationOfBodyMassIndex(calculationBMI);
        logger.info("Интерпретация индекса массы тела: {}", bmiInterpretation );

        double calculationOfIdealWeight  = calculatorService.calculationOfIdealWeight(userDetails.getUser());
        logger.info("Ваш идеальный вес: {}", calculationOfIdealWeight);

        return ResponseEntity.ok(String.format(
                "Ваша дневная норма калорий: %d \n" +
                        "Белки: %d г \n" +
                        "Жиры: %d г \n" +
                        "Углеводы: %d г\n" +
                        "Индекс массы тела: %.2f\n" +
                        "Интерпретация индекса массы тела: %s\n" +
                        "Ваш идеальный вес: %.2f",
                result.getTotalCalories(),
                result.getProtein(),
                result.getFat(),
                result.getCarbs(),
                calculationBMI,
                bmiInterpretation,
                calculationOfIdealWeight
        ));
    }
}
