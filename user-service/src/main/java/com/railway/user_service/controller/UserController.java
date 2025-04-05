package com.railway.user_service.controller;

import com.railway.user_service.dto.LoginDTO;
import com.railway.user_service.dto.ResponseStructureDTO;
import com.railway.user_service.dto.UserRequestDTO;
import com.railway.user_service.dto.UserResponseDTO;
import com.railway.user_service.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService iUserService;

    public UserController(IUserService userService) {
        this.iUserService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseStructureDTO<UserResponseDTO>> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        ResponseStructureDTO<UserResponseDTO> response = iUserService.addUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<ResponseStructureDTO<List<UserResponseDTO>>> getAllUsers(){
        ResponseStructureDTO<List<UserResponseDTO>> allUsersList = iUserService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(allUsersList);
    }

    @PostMapping("/getUserByEmail")
    public ResponseEntity<ResponseStructureDTO<UserResponseDTO>> getUserByEmail(@RequestBody String email) {
        ResponseStructureDTO<UserResponseDTO> response = iUserService.getUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/getUserByUserName")
    public ResponseEntity<ResponseStructureDTO<UserResponseDTO>> getUserByUserName(@RequestBody String userName) {
        ResponseStructureDTO<UserResponseDTO> response = iUserService.getUserByUserName(userName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<ResponseStructureDTO<UserResponseDTO>> userLogin(@Valid @RequestBody LoginDTO loginDTO) {
        ResponseStructureDTO<UserResponseDTO> response = iUserService.userLogin(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
