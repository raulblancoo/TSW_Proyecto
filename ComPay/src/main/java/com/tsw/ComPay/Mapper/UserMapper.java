package com.tsw.ComPay.Mapper;


import ch.qos.logback.core.model.ComponentModel;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Models.UserModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserDto toDto(UserModel userModel);
    UserModel toEntity(UserDto userDto);
}
