package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {

    @Autowired
    private UserMealService service;

    LoggedUser user;

    public void setUser(LoggedUser user) {
        this.user = user;
    }

    public void save(UserMeal userMeal){
        service.save(userMeal, user.id());
    }

    public void delete(int id){
        service.delete(id, user.id());
    }

    public UserMeal get(int id){
        return service.get(id, user.id());
    }

    public List<UserMealWithExceed> getAll(){
        return UserMealsUtil.getWithExceeded(service.getAll(user.id()), user.getCaloriesPerDay());
    }

    public List<UserMealWithExceed> getFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        return UserMealsUtil.getFilteredWithExceeded(service.getAll(user.id()),startDate, startTime, endDate, endTime, user.getCaloriesPerDay());
    }

}
