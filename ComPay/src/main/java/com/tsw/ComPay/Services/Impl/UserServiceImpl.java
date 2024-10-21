package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Mapper.UserMapper;
import com.tsw.ComPay.Models.UserModel;
import com.tsw.ComPay.Repositories.UserRepository;
import com.tsw.ComPay.Services.UserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    private final UserMapper userMapper;



    public void saveUser(UserDto userDto) {

        userRepository.save(userMapper.toEntity(userDto));
    }

    public UserDto findByEmailPassword(String email, String password) {
        return userMapper.toDto(userRepository.findUserModelByEmailAndPassword(email, password));

    }

    public UserDto findByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email));
    }

    public UserDto existingUser(UserDto userDto) {
        return userMapper.toDto( existingUserModel(userDto));
    }

    public UserModel existingUserModel(UserDto userDto) {
        return userRepository.findByid(userDto.getId());
    }
}
