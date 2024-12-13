package fr.scrumtogether.scrumtogetherapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.scrumtogether.scrumtogetherapi.entities.User;

import java.util.Optional;

/**
 * UserRepository is a Spring Data JPA repository that provides CRUD operations and
 * additional query methods for the {@code User} entity.
 * <p>
 * This interface includes custom methods for interacting with the database,
 * specifically tailored to the {@code User} entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by their unique username.
     * <p>
     * @param username the username of the user to be retrieved; must not be null or empty.
     * @return an {@code Optional} containing the user if found, or empty if no user exists with the given username.
     */
    Optional<User> findByUsername(String username);

    boolean existsByUsernameIgnoreCase(String username);
    boolean existsByEmailIgnoreCase(String email);
}