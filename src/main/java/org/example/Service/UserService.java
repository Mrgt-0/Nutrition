package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.DTO.UserDTO;
import org.example.Mapper.UserMapper;
import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO addUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null)
            throw new RuntimeException("Пользователь с таким email уже существует: " + userDTO.getEmail());

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);

        User registredUser = userMapper.toEntity(userDTO);
        registredUser.setActivityLevel(userDTO.getActivityLevel());
        registredUser = userRepository.save(registredUser);
        logger.info("Пользователь успешно зарегистрирован: {}", registredUser.getEmail());
        return userMapper.toDTO(registredUser);
    }

    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            logger.error("Пользователь не найден: {}", email);
            return null;
        }
        return userMapper.toDTO(user);
    }
}