package Service;

import static org.junit.jupiter.api.Assertions.*;
import org.example.DTO.DailyCalorieAndMacroDTO;
import org.example.DTO.UserDTO;
import org.example.Enum.Gender;
import org.example.Enum.Target;
import org.example.Service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {
    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void testCalculateDailyCaloriesAndMacros_Male_LOSE_WEIGHT() {
        UserDTO userDTO = new UserDTO();
        userDTO.setGender(Gender.MALE);
        userDTO.setWeight(70);
        userDTO.setHeight(175);
        userDTO.setAge(25);
        userDTO.setActivityLevel(1.55);
        userDTO.setTarget(Target.LOSE_WEIGHT);
        DailyCalorieAndMacroDTO result = calculatorService.calculateDailyCaloriesAndMacros(userDTO);

        assertNotNull(result);
        assertTrue(result.getTotalCalories() < 2450);
    }

    @Test
    void testCalculateDailyCaloriesAndMacros_Female_MAINTAIN_WEIGHT() {
        UserDTO userDTO = new UserDTO();
        userDTO.setGender(Gender.FEMALE);
        userDTO.setWeight(60);
        userDTO.setHeight(165);
        userDTO.setAge(30);
        userDTO.setActivityLevel(1.4);
        userDTO.setTarget(Target.MAINTAIN_WEIGHT);
        DailyCalorieAndMacroDTO result = calculatorService.calculateDailyCaloriesAndMacros(userDTO);

        assertNotNull(result);
        assertEquals(1937, result.getTotalCalories(), 50);
    }

    @Test
    void testGetDailyCalorieLimit() {
        UserDTO userDTO = new UserDTO();
        userDTO.setGender(Gender.MALE);
        userDTO.setWeight(80);
        userDTO.setHeight(180);
        userDTO.setAge(30);
        userDTO.setActivityLevel(1.2);

        double dailyCalorieLimit = calculatorService.getDailyCalorieLimit(userDTO);
        assertNotNull(dailyCalorieLimit);
        assertTrue(dailyCalorieLimit > 0);
    }

    @Test
    void testBodyMassIndexCalculation() {
        UserDTO userDTO = new UserDTO();
        userDTO.setWeight(70);
        userDTO.setHeight(175);

        double bmi = calculatorService.bodyMassIndexCalculation(userDTO);
        assertEquals(22.86, bmi, 0.01);
    }

    @Test
    void testCalculationOfIdealWeight_Male() {
        UserDTO userDTO = new UserDTO();
        userDTO.setGender(Gender.MALE);
        userDTO.setHeight(180);

        int idealWeight = calculatorService.calculationOfIdealWeight(userDTO);
        assertEquals(92, idealWeight);
    }

    @Test
    void testCalculationOfIdealWeight_Female() {
        UserDTO userDTO = new UserDTO();
        userDTO.setGender(Gender.FEMALE);
        userDTO.setHeight(160);
        int idealWeight = calculatorService.calculationOfIdealWeight(userDTO);
        assertEquals(57, idealWeight);
    }

    @Test
    void testCalculateBMR_Female() {
        UserDTO userDTO = new UserDTO();
        userDTO.setGender(Gender.FEMALE);
        userDTO.setWeight(70);
        userDTO.setHeight(165);
        userDTO.setAge(30);

        double bmr = calculatorService.calculateBMR(userDTO);
        assertEquals(1476.1529999999998, bmr, 0.01);
    }

    @Test
    void testCalculateBMR_Male() {
        UserDTO userDTO = new UserDTO();
        userDTO.setGender(Gender.MALE);
        userDTO.setWeight(75);
        userDTO.setHeight(175);
        userDTO.setAge(30);

        double bmr = calculatorService.calculateBMR(userDTO);
        assertEquals(1762.652, bmr, 0.01);
    }

    @Test
    void testAdjustCaloriesForTarget_LOSE_WEIGHT() {
        double adjustedCalories = calculatorService.adjustCaloriesForTarget(2000, Target.LOSE_WEIGHT);
        assertEquals(1500, adjustedCalories);
    }

    @Test
    void testAdjustCaloriesForTarget_GAIN_WEIGHT() {
        double adjustedCalories = calculatorService.adjustCaloriesForTarget(2000, Target.GAIN_WEIGHT);
        assertEquals(2250, adjustedCalories);
    }

    @Test
    void testAdjustCaloriesForTarget_MAINTAIN_WEIGHT() {
        double adjustedCalories = calculatorService.adjustCaloriesForTarget(2000, Target.MAINTAIN_WEIGHT);
        assertEquals(2000, adjustedCalories);
    }

    @Test
    void testCalculateMacros() {
        DailyCalorieAndMacroDTO macros = calculatorService.calculateMacros(2000, Target.MAINTAIN_WEIGHT);
        assertNotNull(macros);
        assertEquals(2000, macros.getTotalCalories());
        assertEquals(150, macros.getProtein());
        assertEquals(56, macros.getFat());
        assertEquals(225, macros.getCarbs());
    }
}