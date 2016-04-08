package ru.javawebinar.topjava.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

//@ContextConfiguration({
//        "classpath:spring/spring-app.xml",
//        "classpath:spring/spring-db.xml"
//})
//@RunWith(SpringJUnit4ClassRunner.class)
//@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles({Profiles.POSTGRES, Profiles.JDBC})
public class UserMealServiceJDBCTest extends UserMealServiceTest{


    @Test
    @Override
    public void testDelete() throws Exception {
        super.testDelete();
    }

    @Test
    @Override
    public void testDeleteNotFound() throws Exception {
        super.testDeleteNotFound();
    }

    @Test
    @Override
    public void testSave() throws Exception {
        super.testSave();
    }

    @Test
    @Override
    public void testGet() throws Exception {
        super.testGet();
    }

    @Override
    public void testGetNotFound() throws Exception {
        super.testGetNotFound();
    }

    @Test
    @Override
    public void testUpdate() throws Exception {
        super.testUpdate();
    }

    @Test
    @Override
    public void testNotFoundUpdate() throws Exception {
        super.testNotFoundUpdate();
    }

    @Test
    @Override
    public void testGetAll() throws Exception {
        super.testGetAll();
    }

    @Test
    @Override
    public void testGetBetween() throws Exception {
        super.testGetBetween();
    }

}