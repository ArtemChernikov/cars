package ru.job4j.cars.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 01.06.2024
 */
@RequiredArgsConstructor
@Repository
public class CarRepositoryImpl implements CarRepository {
    private final CrudRepository crudRepository;

}
