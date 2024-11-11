package com.dreamcart.dreamshop.service.user;

import com.dreamcart.dreamshop.dto.OrderDto;
import com.dreamcart.dreamshop.dto.UserDto;
import com.dreamcart.dreamshop.model.Order;
import com.dreamcart.dreamshop.model.User;
import com.dreamcart.dreamshop.request.CreateUserRequest;
import com.dreamcart.dreamshop.request.UserUpdateRequest;

import java.util.List;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);


}
