package org.example.springdemoapi.Service;

import org.example.springdemoapi.Dto.UserCreationRequest;
import org.example.springdemoapi.Dto.UserUpdateRequest;
import org.example.springdemoapi.Entity.User;
import org.example.springdemoapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createQuest(UserCreationRequest request){

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found") );
    }

    public User updateUser(String userId, UserUpdateRequest request){
        User user = getUserById(userId);
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

}
