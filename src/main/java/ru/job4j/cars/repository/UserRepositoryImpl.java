package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс-репозиторий для работы с БД
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 24.04.2023
 */
@AllArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final CrudRepository crudRepository;

    /**
     * Метод используется для записи пользователя в БД
     *
     * @param user - новый пользователь
     * @return - возвращает записанного пользователя с проставленным id
     */
    @Override
    public User create(User user) {
//        Session session = sessionFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.save(user);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//        return user;
        crudRepository.run(session -> session.persist(user));
        return user;
    }

    /**
     * Метод используется для обновления данных о пользователе в БД
     *
     * @param user - новый данные о пользователе
     */
    @Override
    public void update(User user) {
        crudRepository.run(session -> session.merge(user));
    }

    /**
     * Метод используется для удаления пользователя из БД по id
     *
     * @param userId - id пользователя
     */
    @Override
    public void delete(int userId) {
        crudRepository.run("DELETE User WHERE id = :id", Map.of("id", userId));
    }

    /**
     * Метод используется для поиска всех пользователей, отсортированных по id по возрастанию
     *
     * @return - возвращает список пользователей
     */
    @Override
    public List<User> findAllOrderById() {
        return crudRepository.query("FROM User order by id ASC", User.class);
    }

    /**
     * Метод используется для поиска пользователя в БД по id
     *
     * @return - возвращает пользователя обернутого в {@link Optional}
     */
    @Override
    public Optional<User> findById(int userId) {
        return crudRepository.optional("FROM User WHERE id = :id", User.class, Map.of("id", userId));
    }

    /**
     * Метод используется для поиска всех пользователей с похожим логином в БД (LIKE %key%)
     *
     * @param key- логин пользователя
     * @return - возвращает список пользователей
     */
    @Override
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query("FROM User WHERE login LIKE :key", User.class, Map.of("key", "%" + key + "%"));
    }

    /**
     * Метод используется для поиска пользователя в БД по логину
     *
     * @param login - логин пользователя
     * @return - возвращает пользователя обернутого в {@link Optional}
     */
    @Override
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional("FROM User WHERE login = :login", User.class, Map.of("login", login));
    }
}
