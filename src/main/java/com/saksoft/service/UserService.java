package com.saksoft.service;

import com.saksoft.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    List<UserDto> getAllUser();

    UserDto updateUser(UserDto userDto, Long id);

    void deleteUser(Long id);
}
