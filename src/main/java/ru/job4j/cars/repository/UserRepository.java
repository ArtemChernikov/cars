package ru.job4j.cars.repository;

import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Общий интерфейс для всех репозиториев с пользователями {@link User}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 24.04.2023
 */
public interface UserRepository {
    /**
     * Метод используется для записи пользователя в БД
     *
     * @param user - новый пользователь
     * @return - возвращает записанного пользователя с проставленным id
     */
    User create(User user);

    /**
     * Метод используется для обновления данных о пользователе в БД
     *
     * @param user - новый данные о пользователе
     */
    void update(User user);

    /**
     * Метод используется для удаления пользователя из БД по id
     *
     * @param userId - id пользователя
     */
    void delete(int userId);

    /**
     * Метод используется для поиска всех пользователей, отсортированных по id
     *
     * @return - возвращает список пользователей
     */
    List<User> findAllOrderById();

    /**
     * Метод используется для поиска пользователя в БД по id
     *
     * @return - возвращает пользователя обернутого в {@link Optional}
     */
    Optional<User> findById(int userId);

    /**
     * Метод используется для поиска всех пользователей с похожим логином в БД (LIKE %key%)
     *
     * @param key- логин пользователя
     * @return - возвращает список пользователей
     */
    List<User> findByLikeLogin(String key);

    /**
     * Метод используется для поиска пользователя в БД по логину
     *
     * @param login - логин пользователя
     * @return - возвращает пользователя обернутого в {@link Optional}
     */
    Optional<User> findByLogin(String login);
}
