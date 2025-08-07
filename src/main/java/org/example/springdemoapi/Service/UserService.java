package org.example.springdemoapi.Service;

import lombok.RequiredArgsConstructor;
import org.example.springdemoapi.Dto.Request.UserCreationRequest;
import org.example.springdemoapi.Dto.Request.UserUpdateRequest;
import org.example.springdemoapi.Dto.Response.ResGetUser;
import org.example.springdemoapi.Entity.User;
import org.example.springdemoapi.Enum.ErrorCode.ErrorCode;
import org.example.springdemoapi.Exception.AppException;
import org.example.springdemoapi.Mapper.UserMapper;
import org.example.springdemoapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //--> Annotation nay se define toan bo cac datatype ma ta define final
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User createQuest(UserCreationRequest request){


        //? Check user da ton tai hay chua
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);


        //? Sử dụng Mapper để làm nhanh thao tác response hơn
        User user = userMapper.toUser(request);

        //? Cách sử dụng thuần túy get đơn giản để trả về repsonse
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public ResGetUser getUserById(String id){
        return userMapper.toGetUser(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public ResGetUser updateUser(String userId, UserUpdateRequest request){

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);

//        user.setUsername(request.getUsername());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());

        return userMapper.toGetUser(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

}
