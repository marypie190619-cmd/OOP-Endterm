package com.example.recipes.service.Impl;

import com.example.recipes.model.dto.UserDTO;
import com.example.recipes.model.entity.User;
import com.example.recipes.repository.UserRepository;
import com.example.recipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public UserDTO create(UserDTO dto) {
        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .Age(dto.getAge())
                .phone(dto.getPhone())
                .build();

        user = userRepository.save(user);
        return mapToDTO(user);
    }
    @Override
    public UserDTO getById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO update(long id, UserDTO dto) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (dto.getFirstName() != null) {
            existingUser.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            existingUser.setLastName(dto.getLastName());
        }
        if (dto.getAge() > 0) {
            existingUser.setAge(dto.getAge());
        }

        if (dto.getPhone() != null) {
            existingUser.setPhone(dto.getPhone());
        }


        User updatedUser = userRepository.save(existingUser);

        return mapToDTO(updatedUser);
    }

    @Override
    public void delete(long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }


        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .Age(user.getAge())
                .phone(user.getPhone())
                .build();
    }
}