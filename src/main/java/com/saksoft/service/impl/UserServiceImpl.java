package com.saksoft.service.impl;

import com.saksoft.dto.UserDto;
import com.saksoft.exception.ResourceNotFoundException;
import com.saksoft.helper.Constants;
import com.saksoft.model.User;
import com.saksoft.repository.UserRepository;
import com.saksoft.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        User saveUser = this.userRepository.save(user);
        return this.modelMapper.map(saveUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> allUser = this.userRepository.findAll();
        List<UserDto> userDtos = allUser.stream().map((user) -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Constants.NOT_FOUND + id));
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(user.getAge());

        User updatedUser = this.userRepository.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Constants.NOT_FOUND + id));
        this.userRepository.delete(user);

    }
}
