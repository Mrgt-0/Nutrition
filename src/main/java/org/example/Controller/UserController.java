package org.example.Controller;

import org.example.DTO.UserDTO;
import org.example.Enum.Gender;
import org.example.Enum.Target;
import org.example.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<?> registerUser(@RequestParam String name,
                                          @RequestParam String email,
                                          @RequestParam String password,
                                          @RequestParam Integer age,
                                          @RequestParam double weight,
                                          @RequestParam double height,
                                          @RequestParam String target,
                                          @RequestParam String gender,
                                          @RequestParam double activityLevel) {
        logger.info("Попытка регистрации нового пользователя с email: {}", email);
        try {
            Target enumTarget = Target.fromValue(target);
            Gender enumGender = Gender.fromValue(gender);
            UserDTO userDTO = new UserDTO(name, email, password, age, weight, height, enumTarget, enumGender, activityLevel);
            userService.addUser(userDTO);
            return ResponseEntity.ok("Регистрация прошла успешно! Пожалуйста, войдите.");
        } catch (RuntimeException e) {
            logger.error("Ошибка при регистрации: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Ошибка регистрации: " + e.getMessage());
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        logger.info("Email: {}", email);

        if (email == null) {
            logger.error("Имя пользователя не указано!");
            return ResponseEntity.badRequest().body("Имя пользователя не может быть пустым");
        }

        if (password == null) {
            logger.error("Пароль не был предоставлен!");
            return ResponseEntity.badRequest().body("Пароль не может быть пустым");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            logger.info("Пользователь с email {} успешно вошел в систему.", email);
            return ResponseEntity.ok("Аутентификация успешна!");
        } catch (BadCredentialsException e) {
            logger.error("Неверные учетные данные для пользователя {}", email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный email или пароль");
        }
    }
}
