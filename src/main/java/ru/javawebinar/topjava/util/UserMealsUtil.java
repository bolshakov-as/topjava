package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        // TODO return filtered list with correctly exceeded field

        Map<LocalDate, Integer> caloriesInDay = new HashMap<>();
        Map<LocalDate, List<UserMeal>> mealInDay = new TreeMap<>();

        for (UserMeal userMeal: mealList){

            LocalDate dateBegin = userMeal.getDateTime().toLocalDate();

            if(TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)){
                if(mealInDay.containsKey(dateBegin)){
                    mealInDay.get(dateBegin).add(userMeal);
                }
                else {
                    List<UserMeal> list = new ArrayList<>();
                    list.add(userMeal);
                    mealInDay.put(dateBegin,list);
                }
            }

            if(caloriesInDay.containsKey(dateBegin)){
                caloriesInDay.put(dateBegin, caloriesInDay.get(dateBegin) + userMeal.getCalories());
            }
            else {
                caloriesInDay.put(dateBegin,userMeal.getCalories());
            }

        }

        List<UserMealWithExceed> res = new ArrayList<>();

        for (Map.Entry<LocalDate, List<UserMeal>> dayMeal: mealInDay.entrySet()){
            boolean ex = caloriesInDay.get(dayMeal.getKey()) > caloriesPerDay;
            for (UserMeal userMeal: dayMeal.getValue()) {
                res.add(new UserMealWithExceed(userMeal.getDateTime(),
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        ex));
            }
        }

        return res;
    }
}
