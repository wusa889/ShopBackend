package ch.csbe.productstore.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Represents a User in the Database.
 */
@RepositoryRestResource(exported = false)
@Entity
public class User {
    @Id // Specifies the primary key of an entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Provides specification of generation strategies for primary keys.
    private Long id;

    // Username of User
    @NotBlank
    @Column(columnDefinition = "varchar(100)")
    private String username;

    // Password of User
    @NotBlank
    @Column(columnDefinition = "varchar(100)")
    private String password;

    // Represents Admin status
    @Column
    private boolean admin;

    // Getters and Setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }
}

