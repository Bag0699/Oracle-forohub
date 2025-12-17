package com.bag.foro_hub.mapper;

import com.bag.foro_hub.model.dto.response.UserResponse;
import com.bag.foro_hub.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserResponse toUserResponse(User user);
}
