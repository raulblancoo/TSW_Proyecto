package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.GroupMembersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMembersRepository extends JpaRepository<GroupMembersModel, Long> {
}
