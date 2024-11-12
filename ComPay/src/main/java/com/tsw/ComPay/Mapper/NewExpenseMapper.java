package com.tsw.ComPay.Mapper;

import com.tsw.ComPay.Dto.NewExpenseDto;
import com.tsw.ComPay.Models.ExpensesModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface NewExpenseMapper {

    @Mapping(source = "expense_name", target="name")
    NewExpenseDto toDto(ExpensesModel expense);


    @Mapping(source = "name", target="expense_name")
    ExpensesModel toEntity(NewExpenseDto expense);
}