package ch.csbe.productstore.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository Interface for Users
 */
@RepositoryRestResource(exported = false)
@Repository

/**
 * Finds a User by Username
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}