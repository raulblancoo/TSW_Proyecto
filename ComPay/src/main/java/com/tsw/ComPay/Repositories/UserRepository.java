package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    //TODO: QUERYS y nombre específico

     UserModel findUserModelByEmailAndPassword(String email, String password);
     UserModel findByEmail(String email);
     UserModel findByid(Long id);
}
