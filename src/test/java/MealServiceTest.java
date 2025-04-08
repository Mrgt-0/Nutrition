package Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.example.Model.Dish;
import org.example.Model.Meal;
import org.example.Model.User;
import org.example.Repository.DishRepository;
import org.example.Repository.MealRepository;
import org.example.Service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MealServiceTest {
    @InjectMocks
    private MealService mealService;

    @Mock
    private MealRepository mealRepository;

    @Mock
    private DishRepository dishRepository;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("test@example.com");
    }

    @Test
    void testCreateMeal_Success() {
        String mealName = "Ужин";
        List<String> dishNames = List.of("Сырники", "Салат");
        LocalDate date = LocalDate.now();

        List<Dish> dishes = new ArrayList<>();
        Dish dish1 = new Dish();
        dish1.setName("Сырники");
        Dish dish2 = new Dish();
        dish2.setName("Салат");
        dishes.add(dish1);
        dishes.add(dish2);

        when(dishRepository.findByName(anyString())).thenReturn(dishes);
        when(mealRepository.save(any(Meal.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Meal result = mealService.createMeal(mealName, dishNames, date, user);

        assertNotNull(result);
        assertEquals(mealName, result.getMealName());
        assertEquals(2, result.getDishes().size());
        verify(mealRepository).save(any(Meal.class));
        verify(dishRepository, times(dishNames.size())).findByName(anyString());
    }

    @Test
    void testCreateMeal_NoDishes() {
        String mealName = "Ужин";
        List<String> dishNames = List.of("Неизвестное Блюдо");
        LocalDate date = LocalDate.now();
        when(dishRepository.findByName(anyString())).thenReturn(new ArrayList<>());

        Meal mealToSave = new Meal();
        mealToSave.setMealName(mealName);
        mealToSave.setDate(date);
        mealToSave.setUser(user);
        mealToSave.setDishes(new HashSet<>());

        when(mealRepository.save(any(Meal.class))).thenReturn(mealToSave);
        Meal result = mealService.createMeal(mealName, dishNames, date, user);

        assertNotNull(result);
        assertEquals(mealName, result.getMealName());
        assertEquals(0, result.getDishes().size());
        verify(mealRepository).save(any(Meal.class));
        verify(dishRepository, times(dishNames.size())).findByName(anyString());
    }
}