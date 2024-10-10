package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    //TODO: QUERYS y nombre específico

    public UserModel findUserModelByEmailAndPassword(String email, String password);
}
