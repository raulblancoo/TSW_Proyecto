package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Long> {
    GroupModel findByGroupName(String name);
    List<GroupModel> findAll();
    GroupModel findGroupModelById(Long id);

    @Query("SELECT gmm.group FROM GroupMembersModel gmm " +
            "JOIN gmm.user us " +
            "WHERE gmm.group.id = :id")
    List<GroupModel> findAllByUserId(@Param("id") Long id);
}
