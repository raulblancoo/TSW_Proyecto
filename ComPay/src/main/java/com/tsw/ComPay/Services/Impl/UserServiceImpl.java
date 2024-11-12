package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.ExpensesShareDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Mapper.UserMapper;
import com.tsw.ComPay.Models.UserModel;
import com.tsw.ComPay.Repositories.UserRepository;
import com.tsw.ComPay.Services.ExpenseShareService;
import com.tsw.ComPay.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    private final UserMapper userMapper;
    private final ExpenseShareService expenseShareService;


    public void saveUser(UserDto userDto) {

        userRepository.save(userMapper.toNewEntity(userDto, passwordEncoder.encode(userDto.getPassword())));
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

    @Override
    public UserDto findByUsernameAndPassword(String username, String password) {
        return userMapper.toDto(userRepository.findByUsernameAndPassword(username, password));
    }

    public UserModel existingUserModel(UserDto userDto) {
        return userRepository.findByid(userDto.getId());
    }

    public UserDto findByUserId(Long id) {
        return userMapper.toDto(userRepository.findByid(id));
    }

    @Override
    public List<UserDto> getUserByExpenseId(Long id) {
        return expenseShareService.findByExpenseId(id).stream().map(ExpensesShareDto::getDestinyUser).toList();
    }
}
