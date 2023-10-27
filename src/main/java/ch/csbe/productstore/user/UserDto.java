package ch.csbe.productstore.user;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing a User.
 * Used to transfer data between the application layers without exposing the entire domain model.
 */
public class UserDto {

    // Username of User cant be null
    @NotBlank
    private String username;

    // Password of User cant be null
    @NotBlank
    private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
