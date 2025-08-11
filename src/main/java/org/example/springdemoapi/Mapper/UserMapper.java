package org.example.springdemoapi.Mapper;

import org.example.springdemoapi.Dto.Request.UserCreationRequest;
import org.example.springdemoapi.Dto.Request.UserUpdateRequest;
import org.example.springdemoapi.Dto.Response.ResGetUser;
import org.example.springdemoapi.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);


//    @Mapping(source = "firstName", target = "lastName") // --> Map cho 2 object trung nhau
    @Mapping(target = "lastName", ignore = true) // --> ignore value trong targer
    ResGetUser toGetUser(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

}
