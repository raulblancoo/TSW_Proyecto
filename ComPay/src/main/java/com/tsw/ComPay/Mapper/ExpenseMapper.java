package com.tsw.ComPay.Mapper;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Models.ExpensesModel;
import com.tsw.ComPay.Models.GroupModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface ExpenseMapper {
    ExpensesDto toDto(ExpensesModel expense);
    ExpensesModel toEntity(ExpensesDto expense);
    List<ExpensesDto> toListDto(List<ExpensesModel> expenses);
}
