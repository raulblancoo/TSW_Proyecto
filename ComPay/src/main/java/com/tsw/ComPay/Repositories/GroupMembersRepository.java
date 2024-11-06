package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.GroupMembersModel;
import com.tsw.ComPay.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMembersRepository extends JpaRepository<GroupMembersModel, Long> {
    List<GroupMembersModel> findByUser(UserModel user);
}
