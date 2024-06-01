package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 24.04.2023
 */
@AllArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final CrudRepository crudRepository;

    @Override
    public User create(User user) {
        crudRepository.run(session -> session.persist(user));
        return user;
    }

    @Override
    public void update(User user) {
        crudRepository.run(session -> session.merge(user));
    }

    @Override
    public void delete(int userId) {
        crudRepository.run("DELETE User WHERE id = :id", Map.of("id", userId));
    }

    @Override
    public List<User> findAllOrderById() {
        return crudRepository.query("FROM User order by id ASC", User.class);
    }

    @Override
    public Optional<User> findById(int userId) {
        return crudRepository.optional("FROM User WHERE id = :id", User.class, Map.of("id", userId));
    }

    @Override
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query("FROM User WHERE login LIKE :key", User.class, Map.of("key", "%" + key + "%"));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional("FROM User WHERE login = :login", User.class, Map.of("login", login));
    }
}
