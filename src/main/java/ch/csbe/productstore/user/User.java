package ch.csbe.productstore.user;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "varchar(100)")
    private String username;

    @Column(columnDefinition = "varchar(100)")
    private String password;

    @Column
    private boolean admin;

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

    public void setAdmin(boolean powerLevel) {
        this.admin = admin;
    }

    public boolean isAdmin(){
        return admin;
    }
}

