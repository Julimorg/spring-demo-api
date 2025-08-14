package org.example.springdemoapi.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springdemoapi.Dto.Request.PermissionRequest;
import org.example.springdemoapi.Dto.Response.PermissionResponse;
import org.example.springdemoapi.Entity.Permission;
import org.example.springdemoapi.Mapper.PermissionMapper;
import org.example.springdemoapi.Repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PremissionService {

    @Autowired
    private final PermissionRepository permissionRepository;

    @Autowired
    private final PermissionMapper permissionMapper;

    public PermissionResponse createPermission(PermissionRequest request)
    {
        Permission permission = permissionMapper.toCreatePermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toCreatePermissionResponse(permission);
    }


    public List<PermissionResponse> getPermission(){
        var permission = permissionRepository.findAll();

        return permission.stream().map(permissionMapper::toGetPermission).toList();
    }

    public void deletePermission(String permissionId){
        permissionRepository.deleteById(permissionId);
    }
}
