package com.tsw.ComPay.Mapper;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewExpenseDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Models.ExpensesModel;
import com.tsw.ComPay.Models.GroupModel;
import com.tsw.ComPay.Services.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel="spring")
public interface NewExpenseMapper {

    @Mapping(source = "expenses_name", target="name")
    NewExpenseDto toDto(ExpensesModel expense);


    @Mapping(source = "name", target="expenses_name")
    ExpensesModel toEntity(NewExpenseDto expense);
}