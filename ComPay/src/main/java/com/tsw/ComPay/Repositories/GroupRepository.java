package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Long> {
    GroupModel findByGroupName(String name);
    List<GroupModel> findAll();
    GroupModel findGroupModelById(Long id);
}
