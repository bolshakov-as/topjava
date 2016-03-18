package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    public UserMeal save(UserMeal userMeal, int idUser);
    public void delete(int id, int idUser);
    public UserMeal get(int id, int idUser);
    public Collection<UserMeal> getAll(int idUser);

}
