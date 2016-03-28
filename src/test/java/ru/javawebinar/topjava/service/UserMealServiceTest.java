package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.MealTestData.*;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bolshakov-as on 28.03.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal test = new UserMeal(100002, LocalDateTime.of(2016, Month.MARCH, 25, 11, 0), "Zavtrak", 500);
        UserMeal prov = service.get(100002, 100000);
        MealTestData.MATCHER.assertEquals(prov, test);
    }

    @Test
    public void testDelete() throws Exception {
        UserMeal test = new UserMeal(100003, LocalDateTime.of(2016, Month.MARCH, 25, 13, 0), "Obed", 1000);
        Collection<UserMeal> listBeforeDelete = service.getAll(100000);
        service.delete(100003,100000);
        listBeforeDelete.remove(test);
        MealTestData.MATCHER.assertCollectionEquals(listBeforeDelete,service.getAll(100000));
    }

    @Test
    public void testGetBetweenDates() throws Exception {

    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testSaveNew() throws Exception {

        UserMeal test = new UserMeal(LocalDateTime.of(2016, Month.MARCH, 25, 13, 0), "Obed", 1000);
        Collection<UserMeal> list = service.getAll(100001);

        UserMeal newMeal = service.save(test, 100001);
        test.setId(newMeal.getId());
        list.add(test);

        MealTestData.MATCHER.assertCollectionEquals(list, service.getAll(100001));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1000003, 100001);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNot() throws Exception {
        UserMeal test = new UserMeal(100003, LocalDateTime.of(2016, Month.MARCH, 25, 13, 0), "Obed", 1000);
        service.update(test,100001);
    }
}