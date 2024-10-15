package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupModel, Long> {
    GroupModel findByGroupName(String name);
}
