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
public class OwnerRepositoryImpl implements OwnerRepository {
    private final CrudRepository crudRepository;
}
