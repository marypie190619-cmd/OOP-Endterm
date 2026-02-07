package com.example.recipes.model.dto;
import lombok .*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private int Age;
    private String phone;

}
