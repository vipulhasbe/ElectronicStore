package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;

import java.util.List;

public interface UserService {

    //  create
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, String userId);

    // delete
    void deleteUser(String userId);

    // get all user
    List<UserDto> getAllUser();

    UserDto getUserById(String userId);
    UserDto getUserByEmail(String userEmail);

    List<UserDto> searchUser(String keyword);
}
