package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        if(userMeal.isNew()){
            User ref = em.getReference(User.class, userId);
            userMeal.setUser(ref);
            em.persist(userMeal);
            return userMeal;
        }else {

            if(em.createNamedQuery(UserMeal.UPDATE)
                    .setParameter("description", userMeal.getDescription())
                    .setParameter("calories", userMeal.getCalories())
                    .setParameter("dateTime", userMeal.getDateTime())
                    .setParameter("id", userMeal.getId())
                    .setParameter("user_id", userId).executeUpdate() != 0){
                User ref = em.getReference(User.class, userId);
                userMeal.setUser(ref);
                return userMeal;
            }

        }

        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {

        Query query = em.createNamedQuery(UserMeal.DELETE);
        query.setParameter("id", id);
        query.setParameter("user_id", userId);

        return query.executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return em.createNamedQuery(UserMeal.GET, UserMeal.class).setParameter("id", id).setParameter("user_id", userId).getSingleResult();
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).setParameter("user_id", userId).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserMeal.GET_BETWEEN, UserMeal.class)
                .setParameter(1, userId)
                .setParameter(2, startDate)
                .setParameter(3, endDate)
                .getResultList();
    }
}