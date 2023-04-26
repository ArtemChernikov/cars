package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Класс-репозиторий для работы с БД
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 24.04.2023
 */
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    /**
     * Метод используется для записи пользователя в БД
     *
     * @param user - новый пользователь
     * @return - возвращает записанного пользователя с проставленным id
     */
    @Override
    public User create(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Метод используется для обновления данных о пользователе в БД
     *
     * @param user - новый данные о пользователе
     */
    @Override
    public void update(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Метод используется для удаления пользователя из БД по id
     *
     * @param userId - id пользователя
     */
    @Override
    public void delete(int userId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE User WHERE id = :id")
                    .setParameter("id", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Метод используется для поиска всех пользователей, отсортированных по id по возрастанию
     *
     * @return - возвращает список пользователей
     */
    @Override
    public List<User> findAllOrderById() {
        Session session = sessionFactory.openSession();
        List<User> users = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User order by id ASC", User.class);
            users = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return users;
    }

    /**
     * Метод используется для поиска пользователя в БД по id
     *
     * @return - возвращает пользователя обернутого в {@link Optional}
     */
    @Override
    public Optional<User> findById(int userId) {
        Session session = sessionFactory.openSession();
        Optional<User> user = Optional.empty();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE id = :id", User.class);
            query.setParameter("id", userId);
            user = query.uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Метод используется для поиска всех пользователей с похожим логином в БД (LIKE %key%)
     *
     * @param key- логин пользователя
     * @return - возвращает список пользователей
     */
    @Override
    public List<User> findByLikeLogin(String key) {
        Session session = sessionFactory.openSession();
        List<User> users = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE login LIKE :key", User.class);
            query.setParameter("key", "%" + key + "%");
            users = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return users;
    }

    /**
     * Метод используется для поиска пользователя в БД по логину
     *
     * @param login - логин пользователя
     * @return - возвращает пользователя обернутого в {@link Optional}
     */
    @Override
    public Optional<User> findByLogin(String login) {
        Session session = sessionFactory.openSession();
        Optional<User> user = Optional.empty();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE login = :login", User.class);
            query.setParameter("login", login);
            user = query.uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }
}
