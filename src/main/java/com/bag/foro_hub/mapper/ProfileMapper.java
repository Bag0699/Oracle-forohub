package com.bag.foro_hub.mapper;

import com.bag.foro_hub.model.dto.request.CreateUserRequest;
import com.bag.foro_hub.model.dto.response.ProfileResponse;
import com.bag.foro_hub.model.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

  Profile toProfile(CreateUserRequest request);

  ProfileResponse toProfileResponse(Profile profile);
}
