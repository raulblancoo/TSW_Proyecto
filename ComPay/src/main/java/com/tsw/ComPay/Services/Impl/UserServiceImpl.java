package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Repositories.UserRepository;
import com.tsw.ComPay.Services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public void saveUser(UserDto user) {

    }
}
