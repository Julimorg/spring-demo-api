package org.example.springdemoapi.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springdemoapi.Dto.ApiResponse;
import org.example.springdemoapi.Dto.Request.PermissionRequest;
import org.example.springdemoapi.Dto.Response.PermissionResponse;
import org.example.springdemoapi.Repository.PermissionRepository;
import org.example.springdemoapi.Service.PremissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@Slf4j
public class PermissionController {

    @Autowired
    private final PermissionRepository permissionRepository;

    @Autowired
    private final PremissionService premissionService;


    @PostMapping("/create-permision")
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .data(premissionService.createPermission(request))
                .build();
    }


    @GetMapping("/get-permission")
    ApiResponse<List<PermissionResponse>> getPermission() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .data(premissionService.getPermission())
                .build();
    }

    @DeleteMapping("/delete-permission")
    ApiResponse<String> deletePermission(){
        return ApiResponse.<String>builder()
                .message("Delete successfull!")
                .build();
    }


}
