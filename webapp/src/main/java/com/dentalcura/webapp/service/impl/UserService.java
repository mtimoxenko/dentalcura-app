package com.dentalcura.webapp.service.impl;


import com.dentalcura.webapp.dto.user.CreateUserRequest;
import com.dentalcura.webapp.dto.user.UpdateUserRequest;
import com.dentalcura.webapp.dto.user.UserResponse;
import com.dentalcura.webapp.model.User;
import com.dentalcura.webapp.repository.IUserRepository;
import com.dentalcura.webapp.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter @Setter
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void insertUser(CreateUserRequest createUserRequest) {
        User user = mapper.convertValue(createUserRequest, User.class);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> selectAllUser() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        for(User user: users){
            userResponses.add(mapper.convertValue(user, UserResponse.class));
        }

        return userResponses;
    }

    @Override
    public UserResponse selectUserByID(Long id) {
        Optional<User> user = userRepository.findById(id);
        UserResponse userResponse = null;

        if(user.isPresent())
            userResponse = mapper.convertValue(user, UserResponse.class);

        return userResponse;
    }

    @Override
    public void updateUserByID(Long id, UpdateUserRequest updateUserRequest) {
        User user = mapper.convertValue(updateUserRequest, User.class);
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void deleteUserByID(Long id) {
        userRepository.deleteById(id);
    }
}
