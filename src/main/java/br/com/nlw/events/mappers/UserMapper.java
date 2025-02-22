package br.com.nlw.events.mappers;

import br.com.nlw.events.dto.UserDto;
import br.com.nlw.events.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")

    UserEntity toModel(UserDto userDto);
    UserDto toDto(UserEntity userEntity);

    List<UserDto> listUserDto(List<UserEntity> users);
}
