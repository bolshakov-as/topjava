package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by bolshakov-as on 28.04.2016.
 */
@RestController
@RequestMapping(UserMealAjaxController.REST_URL)
public class UserMealAjaxController extends AbstractUserMealController {
    public static final String REST_URL = "/ajax/profile/meals";

    @RequestMapping(method = RequestMethod.GET)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUpdate(@RequestParam("id") int id,
                             @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                             @RequestParam("description") String description,
                             @RequestParam("calories") int calories){

        UserMeal userMeal = new UserMeal(dateTime, description, calories);
        if(id == 0) super.create(userMeal);
        else super.update(userMeal, LoggedUser.id());

    }

    @RequestMapping(value = "/between", method = RequestMethod.GET)
    public List<UserMealWithExceed> getBetween(
            @RequestParam(value = "startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam(value = "endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {
        return super.getBetween(startDateTime.toLocalDate(), startDateTime.toLocalTime(), endDateTime.toLocalDate(), endDateTime.toLocalTime());
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public List<UserMealWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate, @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate, @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        return super.getBetween(
                startDate != null ? startDate : TimeUtil.MIN_DATE, startTime != null ? startTime : LocalTime.MIN,
                endDate != null ? endDate : TimeUtil.MAX_DATE, endTime != null ? endTime : LocalTime.MAX);
    }

}
