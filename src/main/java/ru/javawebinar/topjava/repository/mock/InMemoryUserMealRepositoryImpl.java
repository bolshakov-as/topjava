package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {

    private Map<Integer, Map<Integer,UserMeal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(userMeal -> this.save(userMeal, 1));
    }

    @Override
    public UserMeal save(UserMeal userMeal, int idUser) {

        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }

        getUserRepository(idUser).put(userMeal.getId(), userMeal);

        return userMeal;
    }

    @Override
    public void delete(int id, int idUser) {
        getUserRepository(idUser).remove(id);
    }

    @Override
    public UserMeal get(int id, int idUser) {
        return getUserRepository(idUser).get(id);
    }

    @Override
    public Collection<UserMeal> getAll(int idUser) {
        return getUserRepository(idUser).values().stream().sorted(new Comparator<UserMeal>() {
            @Override
            public int compare(UserMeal o1, UserMeal o2) {
                return o1.getDateTime().toLocalTime().compareTo(o2.getDateTime().toLocalTime());
            }
        }).collect(Collectors.toList());
    }

    private Map<Integer,UserMeal> getUserRepository(int idUser){
        if(!repository.containsKey(idUser)) repository.put(idUser, new ConcurrentHashMap<>());
        return repository.get(idUser);
    }

}

