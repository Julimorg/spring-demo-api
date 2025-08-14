package org.example.springdemoapi.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springdemoapi.Dto.Request.CreateRoleRequest;
import org.example.springdemoapi.Dto.Response.RoleResponse;
import org.example.springdemoapi.Entity.Role;
import org.example.springdemoapi.Mapper.RoleMapper;
import org.example.springdemoapi.Repository.PermissionRepository;
import org.example.springdemoapi.Repository.RoleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    @Autowired
    private final RoleMapper roleMapper;

    @Autowired
    private final PermissionRepository permissionRepository;

    @Autowired
    private final RoleRespository roleRespository;

    public RoleResponse createRole(CreateRoleRequest request){
        var role = roleMapper.toCreateRole(request);

        var permission = permissionRepository.findAllById(request.getPermissions());

        role.setPermissions(new HashSet<>(permission));

       role =  roleRespository.save(role);
       return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getRole(){
        var roles = roleRespository.findAll();

        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    void deleteRole(String roleId){
        roleRespository.deleteById(roleId);
    }
}
