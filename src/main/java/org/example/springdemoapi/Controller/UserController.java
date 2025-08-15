package org.example.springdemoapi.Controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.springdemoapi.Dto.ApiResponse;
import org.example.springdemoapi.Dto.Request.UserCreationRequest;
import org.example.springdemoapi.Dto.Request.UserUpdateRequest;
import org.example.springdemoapi.Dto.Response.ResGetUser;
import org.example.springdemoapi.Entity.User;
import org.example.springdemoapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    ApiResponse<User> createUser(@RequestBody  @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.createQuest(request));
        return apiResponse;
    }

    @GetMapping("/get-user")
    ApiResponse<List<User>> getUser(){



        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
         authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));



        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.getUser());
        return apiResponse;
    }

//    @PostAuthorize("")
    @GetMapping("/{userId}")
    ResGetUser getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/edit/{userId}")
    ApiResponse<ResGetUser> updateUserBydId(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return ApiResponse.<ResGetUser>builder()
                .data(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/delete/{userId}")
    String deleteUserById(@PathVariable String userId){
        userService.deleteUser(userId);
        return "User hase been deleted";
    }


}
