package Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.example.DTO.DishDTO;
import org.example.Mapper.DishMapper;
import org.example.Model.Dish;
import org.example.Repository.DishRepository;
import org.example.Service.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

public class DishServiceTest {
    @InjectMocks
    private DishService dishService;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private DishMapper dishMapper;

    private DishDTO dishDTO;
    private Dish dish;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dishDTO = new DishDTO();
        dishDTO.setName("Тесто");
        dishDTO.setCalories(300);
        dishDTO.setProtein(10);
        dishDTO.setFats(5);
        dishDTO.setCarbohydrates(40);

        dish = new Dish();
        dish.setName(dishDTO.getName());
        dish.setCalories(dishDTO.getCalories());
        dish.setProtein(dishDTO.getProtein());
        dish.setFats(dishDTO.getFats());
        dish.setCarbohydrates(dishDTO.getCarbohydrates());
    }

    @Test
    void testCreateDish() {
        dishService.createDish(dishDTO);
        verify(dishRepository).save(any(Dish.class));
    }

    @Test
    void testFindDishesByNames() {
        List<String> dishNames = List.of("Тесто", "Суп");
        List<Dish> foundDishes = new ArrayList<>();
        foundDishes.add(dish);

        when(dishRepository.findByName("Тесто")).thenReturn(foundDishes);
        when(dishMapper.toDTO(dish)).thenReturn(dishDTO);

        List<DishDTO> result = dishService.findDishesByNames(dishNames);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Тесто", result.get(0).getName());

        verify(dishRepository).findByName("Тесто");
        verify(dishRepository).findByName("Суп");
    }

    @Test
    void testFindDishesByNames_NoDishesFound() {
        List<String> dishNames = List.of("Неизвестное блюдо");
        when(dishRepository.findByName("Неизвестное блюдо")).thenReturn(new ArrayList<>());
        List<DishDTO> result = dishService.findDishesByNames(dishNames);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(dishRepository).findByName("Неизвестное блюдо");
    }
}
