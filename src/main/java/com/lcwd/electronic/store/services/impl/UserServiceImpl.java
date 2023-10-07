package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.repositories.UserRepository;
import com.lcwd.electronic.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
         String userId = UUID.randomUUID().toString();
         userDto.setUserId(userId);
         User user = dtoToUser(userDto);
         User savedUser= userRepo.save(user);
         UserDto newDto = entityToDto(savedUser);
        return newDto;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        user.setGender(userDto.getGender());

        userRepo.save(user);
        UserDto updatedUserDto = entityToDto(user);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id"));
        userRepo.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> allUser = userRepo.findAll();
        List<UserDto> allUserDtos = allUser.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return  allUserDtos;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id"));
        UserDto userDto = entityToDto(user);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String userEmail) {
        User user = userRepo.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found with given email"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword){
        List<User> users= userRepo.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        return dtoList;
    }
    private UserDto entityToDto(User savedUser) {
//        return UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName())
//                .build();
        return mapper.map(savedUser,UserDto.class);
    }

    private User dtoToUser(UserDto userDto) {
//       User user =  User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//               .imageName(userDto.getImageName())
//                .gender(userDto.getGender()).build();

       return mapper.map(userDto,User.class);
    }
}
