package Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.example.DTO.DailyReportDTO;
import org.example.DTO.UserDTO;
import org.example.Mapper.UserMapper;
import org.example.Model.Meal;
import org.example.Model.User;
import org.example.Repository.MealRepository;
import org.example.Repository.UserRepository;
import org.example.Service.CalculatorService;
import org.example.Service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ReportServiceTest {
    @InjectMocks
    private ReportService reportService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MealRepository mealRepository;

    @Mock
    private CalculatorService calculatorService;

    @Mock
    private UserMapper userMapper;

    private User user;
    private DailyReportDTO expectedReport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("test@example.com");
        user.setAge(30);
        user.setWeight(70);
        expectedReport = new DailyReportDTO();
    }

    @Test
    void testGenerateDailyReport_UserFound() {
        LocalDate date = LocalDate.now();
        List<Meal> meals = new ArrayList<>();

        Meal meal = new Meal();
        meal.setMealName("Ужин");
        meal.setDishes(new HashSet<>());
        meals.add(meal);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(mealRepository.findByUserAndDate(user, date)).thenReturn(meals);
        when(calculatorService.getDailyCalorieLimit(userMapper.toDTO(user))).thenReturn(2000.0);
        when(userMapper.toDTO(user)).thenReturn(new UserDTO());
        DailyReportDTO result = reportService.generateDailyReport(user.getEmail(), date);
        assertNotNull(result);
        assertEquals(meals.size(), result.getMeals().size());
        assertTrue(result.isWithinLimit());
        assertEquals(meals.get(0).getMealName(), result.getMeals().get(0).getMealName());

        verify(userRepository).findByEmail(user.getEmail());
        verify(mealRepository).findByUserAndDate(user, date);
        verify(calculatorService).getDailyCalorieLimit(any());
    }

    @Test
    void testGenerateDailyReport_UserNotFound() {
        LocalDate date = LocalDate.now();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reportService.generateDailyReport(user.getEmail(), date);
        });
        assertEquals("Пользователь не найден", exception.getMessage());
    }
}