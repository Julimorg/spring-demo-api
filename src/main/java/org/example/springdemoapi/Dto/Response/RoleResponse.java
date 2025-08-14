package org.example.springdemoapi.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springdemoapi.Entity.Permission;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {
    
    private String name;
    private String description;

    Set<PermissionResponse> permissions;

}
