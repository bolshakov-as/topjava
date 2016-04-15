package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {
    @Autowired
    private UserService service;

    @Autowired
    private UserMealService mealService;

    @Autowired
    private UserMealRestController mealController;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", service.getAll());
        return "userList";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        LoggedUser.setId(userId);
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET, params = {"id","action=delete"})
    public String mealDelete(Model model,
        @RequestParam(value = "id") int id) {
        mealService.delete(id, LoggedUser.id());
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET, params = {"id","action=update"})
    public String toFormUpdateMeal(Model model,
                             @RequestParam(value = "id") int id) {
        UserMeal meal = mealService.get(id, LoggedUser.id());
        model.addAttribute("meal", meal);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET, params = "action=create")
    public String toFormCreateMeal(Model model) {
        UserMeal meal = new UserMeal();
        meal.setDateTime(LocalDateTime.now());
        model.addAttribute("meal", meal);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {

        model.addAttribute("mealList", UserMealsUtil.getWithExceeded(mealService.getAll(LoggedUser.id()),LoggedUser.getCaloriesPerDay()));
        return "mealList";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST, params = "action=filter")
    public String mealFilter(Model model, HttpServletRequest request) {

        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request));
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request));
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request));
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request));

        model.addAttribute("mealList", mealController.getBetween(startDate, startTime, endDate, endTime));

        return "mealList";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String mealUpdate(HttpServletRequest request) {

        String strMealId = request.getParameter("id");

        UserMeal userMeal = null;
        if(strMealId.isEmpty()){
            userMeal = new UserMeal();
        }
        else{
            userMeal = mealService.get(Integer.valueOf(strMealId), LoggedUser.id());
        }

        userMeal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
        userMeal.setDescription(request.getParameter("description"));
        userMeal.setCalories(Integer.valueOf(request.getParameter("calories")));

        mealService.save(userMeal, LoggedUser.id());

        return "redirect:meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"), "parameter id  must not be null");
        return Integer.valueOf(paramId);
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }
}
