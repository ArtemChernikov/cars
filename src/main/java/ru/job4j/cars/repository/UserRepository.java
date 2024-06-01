package ru.job4j.cars.repository;

import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 24.04.2023
 */
public interface UserRepository {

    User create(User user);

    void update(User user);

    void delete(int userId);

    List<User> findAllOrderById();

    Optional<User> findById(int userId);

    List<User> findByLikeLogin(String key);

    Optional<User> findByLogin(String login);
}
