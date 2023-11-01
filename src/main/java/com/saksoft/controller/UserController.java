package com.saksoft.controller;

import com.saksoft.dto.UserDto;
import com.saksoft.helper.ApiResponse;
import com.saksoft.helper.Constants;
import com.saksoft.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        logger.info("Entering the request for save user data");
        UserDto saveUser = userService.saveUser(userDto);
        logger.info("Complated the request for save user data");
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUser() {
        logger.info("Entering the request for get all user data");
        List<UserDto> allUser = userService.getAllUser();

        logger.info("Complate the request for get all user data");
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        logger.info("Entering the request for  update the user data with userId {}:", id);
        UserDto updateUser = userService.updateUser(userDto, id);
        logger.info("Complete the request for  update the user data with userId {}:", id);
        return new ResponseEntity<>(updateUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        logger.info("Entering the request for delete the user data with userId {}:", id);
        userService.deleteUser(id);
        logger.info("Completed the request for delete the user data with userId {}:", id);
        return new ResponseEntity<>(new ApiResponse(Constants.USER_DELETE + id, true), HttpStatus.OK);
    }

}
