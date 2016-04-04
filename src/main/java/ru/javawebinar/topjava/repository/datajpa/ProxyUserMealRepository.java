package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by bolshakov-as on 04.04.2016.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Override
    @Transactional
    UserMeal save(UserMeal userMeal);

    @Modifying
    @Transactional
    @Query("UPDATE UserMeal m SET m.description=:description, m.dateTime=:dateTime, m.calories=:calories WHERE m.id = :id AND m.user.id = :userId")
    Integer saveUpdate(@Param("id") int id,
                        @Param("userId") int userId,
                        @Param("description") String description,
                        @Param("dateTime") LocalDateTime dateTime,
                        @Param("calories") int calories);

    @Query("SELECT DISTINCT m FROM UserMeal m WHERE m.id = ?1 AND m.user.id = ?2")
    UserMeal findOne(Integer id, Integer userId);

    @Query("SELECT m FROM UserMeal m WHERE m.user.id = ?1 ORDER BY m.dateTime DESC")
    List<UserMeal> findAll(Integer userId);

    @Query("SELECT m FROM UserMeal m WHERE m.user.id = ?1 AND m.dateTime >= ?2 AND m.dateTime <= ?3 ORDER BY m.dateTime DESC")
    List<UserMeal> findBetween(int userId, LocalDateTime startDate, LocalDateTime endDate);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserMeal m WHERE m.id= ?1 AND m.user.id = ?2")
    int delete(int id,int userId);


}
