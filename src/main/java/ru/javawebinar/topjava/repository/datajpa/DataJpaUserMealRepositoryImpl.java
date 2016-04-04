package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository{

    private static final Sort SORT_DATE = new Sort(Sort.Direction.DESC,"date_time");

    @Autowired
    private ProxyUserMealRepository proxy;

    @Autowired
    private ProxyUserRepository proxyUser;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {

        User user = proxyUser.findOne(userId);
        userMeal.setUser(user);
        if(userMeal.isNew()){
            return proxy.save(userMeal);
        }

        if(proxy.saveUpdate(userMeal.getId(), userId, userMeal.getDescription(), userMeal.getDateTime(), userMeal.getCalories()) > 0)
            return userMeal;

        return null;

    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) > 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxy.findOne(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.findAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.findBetween(userId, startDate, endDate);
    }
}
