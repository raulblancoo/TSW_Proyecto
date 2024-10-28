package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.GroupMembersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMembersRepository extends JpaRepository<GroupMembersModel, Long> {
    List<GroupMembersModel> findByUser(Long id);
}
