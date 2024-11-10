package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Dto.PruebaDto;
import com.tsw.ComPay.Dto.UserAuthDto;
import com.tsw.ComPay.Models.ExpenseShareModel;
import com.tsw.ComPay.Models.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseShareRepository extends JpaRepository<ExpenseShareModel, Long> {
    List<ExpenseShareModel> findByExpense_Id(Long id);
    List<ExpenseShareModel> findByExpense_GroupId(Long id);
    List<ExpenseShareModel> findExpenseShareModelsByExpenseOriginUserId(Long id);
    List<ExpenseShareModel> findExpenseShareModelsByDestinyUserId(Long userAuthId);

//    @Query(
//            value = " SELECT e.id AS expense_id, " +
//                    "        e.amount, " +
//                    "        e.fk_user AS origin_user_id, " +
//                    "        esh.destinyuser_id, " +
//                    "        esh.debt,"  +
//                    "        e.fk_group AS group_id " +
//                    " FROM expenses_share esh " +
//                    " INNER JOIN expenses e  " +
//                    " ON esh.expense_id = e.id " +
//                    " WHERE e.fk_group = :groupId ", nativeQuery = true
//    )
//    List<PruebaDto> prueba(Long groupId);

    @Query(
            value = " SELECT s FROM ExpenseShareModel s WHERE s.expense.group.id = :groupId"
    )
    List<ExpenseShareModel> findExpensesByGroup(Long groupId);
}
