package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal, int idUser);

    void delete(int id, int idUser);

    UserMeal get(int id, int idUser);

    Collection<UserMeal> getAll(int idUser);
}
