package com.example.recipes.service;

import com.example.recipes.model.dto.UserDTO;
import com.example.recipes.model.entity.User;
import com.example.recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    UserDTO create (UserDTO dto);
    UserDTO getById (long id);
    List<UserDTO> getAll();

    UserDTO update(long id, UserDTO dto);
    void delete(long id);
}
