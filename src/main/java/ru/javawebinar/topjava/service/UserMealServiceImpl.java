package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(UserMeal userMeal, int idUser) {
        return repository.save(userMeal, idUser);
    }

    @Override
    public void delete(int id, int idUser) {
        repository.delete(id, idUser);
    }

    @Override
    public UserMeal get(int id, int idUser) {
        return repository.get(id,idUser);
    }

    @Override
    public Collection<UserMeal> getAll(int idUser) {
        return repository.getAll(idUser);
    }

}
