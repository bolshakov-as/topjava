package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static  ru.javawebinar.topjava.MealTestData.*;


/**
 * Created by bolshakov-as on 25.04.2016.
 */
public class UserMealRestControllerTest extends AbstractControllerTest{

    public static final String REST_URL = UserMealRestController.REST_URL + '/';

    @Test
    public void tesGetAll() throws Exception {

        TestUtil.print(mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_EXCEED.contentListMatcher(UserMealsUtil.getWithExceeded(USER_MEALS, LoggedUser.getCaloriesPerDay()))));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(delete(REST_URL + MEAL1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        List<UserMeal> list = new ArrayList<>(USER_MEALS);
        list.remove(MEAL1);

        MATCHER.assertCollectionEquals(list, userMealService.getAll(100000));
    }

    @Test
    public void testUpdate() throws Exception {

        UserMeal updated = new UserMealTest(MEAL1);
        updated.setDescription("UpdateDescription");

        mockMvc.perform(put(REST_URL + MEAL1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, userMealService.get(MEAL1.getId(), 100000));

    }

    @Test
    public void testGetBetween() throws Exception {
      TestUtil.print(mockMvc.perform(get(REST_URL + "/between?startDateTime=2015-05-30T07:00&endDateTime=2015-05-31T11:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MATCHER_EXCEED.contentListMatcher(
                        UserMealsUtil.createWithExceed(MEAL4, true),
                        UserMealsUtil.createWithExceed(MEAL1, false))));
    }
}