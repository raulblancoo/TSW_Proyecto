package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Models.UserModel;
import com.tsw.ComPay.Repositories.UserRepository;
import com.tsw.ComPay.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserDto userDto) {
        UserModel user = new UserModel();

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        userRepository.save(user);
    }

    public UserModel findByEmailPassword(String email, String password) {
        UserModel user = userRepository.findUserModelByEmailAndPassword(email, password);
        return user;
    }
}
