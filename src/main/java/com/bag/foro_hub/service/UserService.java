package com.bag.foro_hub.service;

import com.bag.foro_hub.model.dto.request.CreateUserRequest;
import com.bag.foro_hub.model.dto.response.UserResponse;

import java.util.List;

public interface UserService {

  UserResponse saveUser(CreateUserRequest request);

  UserResponse saveAdmin(CreateUserRequest request);

  List<UserResponse> findAll();
}
