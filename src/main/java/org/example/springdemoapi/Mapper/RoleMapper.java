package org.example.springdemoapi.Mapper;

import org.example.springdemoapi.Dto.Request.CreateRoleRequest;
import org.example.springdemoapi.Dto.Response.RoleResponse;
import org.example.springdemoapi.Entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toCreateRole(CreateRoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
