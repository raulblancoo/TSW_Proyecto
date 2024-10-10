package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Models.UserModel;
import com.tsw.ComPay.Repositories.UserRepository;

public interface UserService {

  void saveUser(UserDto user);
  UserModel findByEmailPassword(String email, String password);

}
