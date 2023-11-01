package com.saksoft.service.impl;

import com.saksoft.dto.UserDto;
import com.saksoft.exception.ResourceNotFoundException;
import com.saksoft.helper.Constants;
import com.saksoft.model.User;
import com.saksoft.repository.UserRepository;
import com.saksoft.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto saveUser(UserDto userDto) {
        log.info("Intiating the dao call for the save user data");
        User user = this.modelMapper.map(userDto, User.class);
        User saveUser = this.userRepository.save(user);
        log.info("Complate the dao call for the save user data");
        return this.modelMapper.map(saveUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        log.info("Intiating the dao call for the get all user data");
        List<User> allUser = this.userRepository.findAll();
        List<UserDto> userDtos = allUser.stream().map((user) -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        log.info("Complate the dao call for the get all user data");
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        log.info("Intiating the dao call for update the user data with userId {} :",id);
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Constants.NOT_FOUND + id));
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(user.getAge());

        User updatedUser = this.userRepository.save(user);
        log.info("Complate the dao call for update the user data with userId {} :",id);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {

        log.info("Intiating the dao call for delete the user data with userId {} :",id);
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Constants.NOT_FOUND + id));

            log.info("Complate the dao call for update the user data with userId {} :",id);
        this.userRepository.delete(user);

    }
}
