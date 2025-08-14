package org.example.springdemoapi.Mapper;


import org.example.springdemoapi.Dto.Request.PermissionRequest;
import org.example.springdemoapi.Dto.Response.PermissionResponse;
import org.example.springdemoapi.Entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toCreatePermission(PermissionRequest request);

    PermissionResponse toCreatePermissionResponse(Permission permission);


    PermissionResponse toGetPermission (Permission permission);

}
