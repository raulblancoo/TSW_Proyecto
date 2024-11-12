package com.tsw.ComPay.Mapper;

import com.tsw.ComPay.Dto.ExpensesShareDto;
import com.tsw.ComPay.Models.ExpenseShareModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface ExpenseShareMapper {
    ExpensesShareDto toDto(ExpenseShareModel expense);
    ExpenseShareModel toEntity(ExpensesShareDto expense);
    List<ExpensesShareDto> toListDto(List<ExpenseShareModel> expenses);
}