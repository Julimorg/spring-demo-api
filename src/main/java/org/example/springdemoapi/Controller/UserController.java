package org.example.springdemoapi.Controller;


import jakarta.validation.Valid;
import org.example.springdemoapi.Dto.ApiResponse;
import org.example.springdemoapi.Dto.UserCreationRequest;
import org.example.springdemoapi.Dto.UserUpdateRequest;
import org.example.springdemoapi.Entity.User;
import org.example.springdemoapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    List<User> getUser(){
        return userService.getUser();
    }

    @GetMapping("/{userId}")
    User getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/edit/{userId}")
    User updateUserBydId(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/delete/{userId}")
    String deleteUserById(@PathVariable String userId){
        userService.deleteUser(userId);
        return "User hase been deleted";
    }


}
