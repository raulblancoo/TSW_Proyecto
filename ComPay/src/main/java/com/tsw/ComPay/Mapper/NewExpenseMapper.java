package com.tsw.ComPay.Mapper;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewExpenseDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Models.ExpensesModel;
import com.tsw.ComPay.Models.GroupModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface NewExpenseMapper {
    NewExpenseDto toDto(ExpensesModel expense);
    ExpensesModel toEntity(NewExpenseDto expense);
}