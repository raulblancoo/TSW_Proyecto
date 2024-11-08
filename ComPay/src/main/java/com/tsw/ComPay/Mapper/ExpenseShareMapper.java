package com.tsw.ComPay.Mapper;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.ExpensesShareDto;
import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Models.ExpenseShareModel;
import com.tsw.ComPay.Models.ExpensesModel;
import com.tsw.ComPay.Models.GroupModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface ExpenseShareMapper {
    ExpensesShareDto toDto(ExpenseShareModel expense);
    ExpenseShareModel toEntity(ExpensesShareDto expense);
    List<ExpensesShareDto> toListDto(List<ExpenseShareModel> expenses);
}