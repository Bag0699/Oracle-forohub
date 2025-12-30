package com.bag.foro_hub.mapper;

import com.bag.foro_hub.model.dto.response.AuthResponse;
import com.bag.foro_hub.security.CustomUserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

  @Mapping(target = "role", source = "roleClean")
  AuthResponse toAuthResponse(CustomUserDetails userDetails, String token, String roleClean);
}
