package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

  void saveUser(com.tsw.ComPay.Dto.UserDto user);
  UserDto findByEmailPassword(String email, String password);
  UserDto findByEmail(String email);
  UserDto existingUser(UserDto userDto);
  UserDto findByUsernameAndPassword(String username, String password);
  UserDto findByUserId(Long id);
  List<UserDto> getUserByExpenseId(Long id);
}
