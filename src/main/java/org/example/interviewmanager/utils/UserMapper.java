package org.example.interviewmanager.utils;

import org.example.interviewmanager.dto.UserDTO;
import org.example.interviewmanager.repository.entity.User;

public class UserMapper {
    public static UserDTO toDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.id());
        user.setPassword(userDTO.password());
        user.setEmail(userDTO.email());
        user.setFullName(userDTO.fullName());
        user.setCreatedAt(userDTO.createdAt());
        user.setUpdatedAt(userDTO.updatedAt());
        return user;
    }
}
