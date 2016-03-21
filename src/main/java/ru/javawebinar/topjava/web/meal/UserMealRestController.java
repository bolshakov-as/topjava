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

    public void save(UserMeal userMeal){
        service.save(userMeal, LoggedUser.id());
    }

    public void delete(int id){
        service.delete(id, LoggedUser.id());
    }

    public UserMeal get(int id){
        return service.get(id, LoggedUser.id());
    }

    public List<UserMealWithExceed> getAll(){
        return UserMealsUtil.getWithExceeded(service.getAll(LoggedUser.id()), LoggedUser.getCaloriesPerDay());
    }

    public List<UserMealWithExceed> getFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        return UserMealsUtil.getFilteredWithExceeded(service.getAll(LoggedUser.id()),startDate, startTime, endDate, endTime, LoggedUser.getCaloriesPerDay());
    }

}
