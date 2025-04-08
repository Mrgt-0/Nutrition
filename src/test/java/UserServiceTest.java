package Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.example.DTO.UserDTO;
import org.example.Mapper.UserMapper;
import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.example.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserDTO userDTO;
    private User userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password");
        userDTO.setActivityLevel(1.2);

        userEntity = new User();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword("encodedPassword");
        userEntity.setActivityLevel(userDTO.getActivityLevel());
    }

    @Test
    void testAddUser_Success() {
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("encodedPassword");

        when(userMapper.toEntity(userDTO)).thenReturn(userEntity);
        when(userRepository.save(any(User.class))).thenReturn(userEntity);
        when(userMapper.toDTO(userEntity)).thenReturn(userDTO);
        UserDTO result = userService.addUser(userDTO);

        verify(userRepository).findByEmail(userDTO.getEmail());
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(any(User.class));
        assertNotNull(result);
        assertEquals(userDTO.getEmail(), result.getEmail());
    }

    @Test
    void testAddUser_UserAlreadyExists() {
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(userEntity);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.addUser(userDTO);
        });

        assertEquals("Пользователь с таким email уже существует: test@example.com", exception.getMessage());
        verify(userRepository).findByEmail(userDTO.getEmail());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testFindByEmail_UserFound() {
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(userEntity);
        when(userMapper.toDTO(userEntity)).thenReturn(userDTO);
        UserDTO result = userService.findByEmail(userDTO.getEmail());
        assertNotNull(result);
        assertEquals(userDTO.getEmail(), result.getEmail());
    }

    @Test
    void testFindByEmail_UserNotFound() {
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(null);
        UserDTO result = userService.findByEmail(userDTO.getEmail());
        assertNull(result);
    }
}