package com.tsw.ComPay.Mapper;

import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserDto toDto(UserModel userModel);
    UserModel toEntity(UserDto userDto);

    @Mapping(source="password", target="password")
    UserModel toNewEntity(UserDto userDto, String password);
}
