package com.bag.foro_hub.mapper;

import com.bag.foro_hub.model.dto.request.CreateUserRequest;
import com.bag.foro_hub.model.dto.response.UserResponse;
import com.bag.foro_hub.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {ProfileMapper.class})
public interface UserMapper {

  User toUser(CreateUserRequest request);

  UserResponse toUserResponse(User user);
}
