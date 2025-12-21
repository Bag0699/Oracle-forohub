package com.bag.foro_hub.service;

import com.bag.foro_hub.exceptions.DuplicateEmailException;
import com.bag.foro_hub.exceptions.UserNotFoundException;
import com.bag.foro_hub.mapper.ProfileMapper;
import com.bag.foro_hub.mapper.UserMapper;
import com.bag.foro_hub.model.dto.request.CreateUserRequest;
import com.bag.foro_hub.model.dto.request.UpdateProfileRequest;
import com.bag.foro_hub.model.dto.response.ProfileResponse;
import com.bag.foro_hub.model.dto.response.UserResponse;
import com.bag.foro_hub.model.entity.Profile;
import com.bag.foro_hub.model.entity.User;
import com.bag.foro_hub.repository.UserRepository;
import com.bag.foro_hub.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final ProfileMapper profileMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public UserResponse saveUser(CreateUserRequest request) {
    if (userRepository.existsByEmail(request.email())) {
      throw new DuplicateEmailException();
    }

    Profile profile = profileMapper.toProfile(request);
    User user = userMapper.toUser(request);

    user.setPassword(passwordEncoder.encode(request.password()));
    user.setRole(Role.USER);
    user.setProfile(profile);
    profile.setUser(user);

    return userMapper.toUserResponse(userRepository.save(user));
  }

  @Override
  @Transactional
  public UserResponse saveAdmin(CreateUserRequest request) {
    if (userRepository.existsByEmail(request.email())) {
      throw new DuplicateEmailException();
    }

    Profile profile = profileMapper.toProfile(request);
    User user = userMapper.toUser(request);

    user.setPassword(passwordEncoder.encode(request.password()));
    user.setRole(Role.ADMIN);
    user.setProfile(profile);
    profile.setUser(user);

    return userMapper.toUserResponse(userRepository.save(user));
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserResponse> findAll() {
    return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public ProfileResponse getProfile(Long authId) {
    User user = userRepository.findById(authId).orElseThrow(UserNotFoundException::new);

    return profileMapper.toProfileResponse(user.getProfile());
  }

  @Override
  @Transactional
  public ProfileResponse updateProfile(Long authId, UpdateProfileRequest request) {
    User user = userRepository.findById(authId).orElseThrow(UserNotFoundException::new);
    Profile profile = user.getProfile();

    profile.setFirstName(request.firstName());
    profile.setLastName(request.lastName());
    profile.setBio(request.bio());

    userRepository.save(user);
    return profileMapper.toProfileResponse(profile);
  }
}
