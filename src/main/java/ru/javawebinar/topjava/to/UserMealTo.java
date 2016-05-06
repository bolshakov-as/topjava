package ru.javawebinar.topjava.to;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by bolshakov-as on 05.05.2016.
 */
public class UserMealTo implements Serializable {

    protected Integer id;

    @NotNull
    protected String dateTime;

    @NotNull
    protected String description;

    @NotNull(message = " must not be empty")
    @Range(min = 10, max = 5000)
    protected int calories;


    public UserMealTo() {
    }

    public UserMealTo(String dateTime, String description, int calories) {
        this.description = description;
        this.calories = calories;
        this.dateTime = dateTime;
    }

    public UserMealTo(int id, String dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "UserMealTo{" +
                "id=" + id +
                ", dateTime='" + dateTime + '\'' +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}



