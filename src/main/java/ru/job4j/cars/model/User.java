package ru.job4j.cars.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 24.04.2023
 */
@Data
@Entity
@Table(name = "auto_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
}
