package com.railway.user_service.service;

import com.railway.user_service.dto.LoginDTO;
import com.railway.user_service.dto.ResponseStructureDTO;
import com.railway.user_service.dto.UserRequestDTO;
import com.railway.user_service.dto.UserResponseDTO;
import com.railway.user_service.entity.User;
import com.railway.user_service.exception.FieldAlreadyExistException;
import com.railway.user_service.repository.IUserRepository;
import com.railway.user_service.utility.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseStructureDTO<UserResponseDTO> addUser(UserRequestDTO userRequestDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());
        Optional<User> existingUsername = userRepository.findByUserName(userRequestDTO.getUserName());

        if (existingUsername.isPresent()) {
            throw new FieldAlreadyExistException("User with username " + userRequestDTO.getUserName() + " already exists!");
        }

        if (existingUser.isPresent()) {
            throw new FieldAlreadyExistException("User with email " + userRequestDTO.getEmail() + " already exists!");
        }

        User user = UserMapper.mapToEntity(userRequestDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User addedUser = userRepository.save(user);

        UserResponseDTO userResponse = UserMapper.mapToDTO(addedUser);

        ResponseStructureDTO<UserResponseDTO> response = new ResponseStructureDTO<>(
                LocalDateTime.now(),
                "User registered successfully!",
                userResponse
        );

        return response;
    }

    @Override
    public ResponseStructureDTO<List<UserResponseDTO>> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        List<UserResponseDTO> allUsersDTO = new ArrayList<>();
        for(User user : allUsers) {
            allUsersDTO.add(UserMapper.mapToDTO(user));
        }

        ResponseStructureDTO<List<UserResponseDTO>> responseDTO = new ResponseStructureDTO<>(
                LocalDateTime.now(),
                "All users fetched successfully",
                allUsersDTO
        );

        return responseDTO;
    }

    @Override
    public ResponseStructureDTO<UserResponseDTO> getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with email " + email + " does not exist!");
        }

        UserResponseDTO userResponse = UserMapper.mapToDTO(user.get());

        ResponseStructureDTO<UserResponseDTO> response = new ResponseStructureDTO<>(
                LocalDateTime.now(),
                "User fetched successfully!",
                userResponse
        );

        return response;
    }

    @Override
    public ResponseStructureDTO<UserResponseDTO> getUserByUserName(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with username " + userName + " does not exist!");
        }

        UserResponseDTO userResponse = UserMapper.mapToDTO(user.get());

        ResponseStructureDTO<UserResponseDTO> response = new ResponseStructureDTO<>(
                LocalDateTime.now(),
                "User fetched successfully!",
                userResponse
        );

        return response;
    }

    @Override
    public ResponseStructureDTO<UserResponseDTO> userLogin(LoginDTO loginDTO){
        String userName = loginDTO.getUserName();
        String password = loginDTO.getPassword();

        Optional<User> user = userRepository.findByUserName(userName);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with username " + userName + " does not exist!");
        }

        User userData = user.get();
        if(!passwordEncoder.matches(password, userData.getPassword())) {
            throw new IllegalArgumentException(("Invalid password!"));
        }

        UserResponseDTO userResponse = UserMapper.mapToDTO(userData);

        ResponseStructureDTO<UserResponseDTO> response = new ResponseStructureDTO<>(
                LocalDateTime.now(),
                "User logged in successfully!",
                userResponse
        );

        return response;
    }
}
