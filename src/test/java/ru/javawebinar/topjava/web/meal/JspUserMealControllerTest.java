package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * Created by bolshakov-as on 22.04.2016.
 */
public class JspUserMealControllerTest extends AbstractControllerTest {

    @Test
    public void testMealList() throws Exception {

//        mockMvc.perform(get("/meals"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("userList"))
//                .andExpect(forwardedUrl("/WEB-INF/jsp/userList.jsp"))
//                .andExpect(model().attribute("userList", hasSize(2)))
//                .andExpect(model().attribute("userList", hasItem(
//                        allOf(
//                                hasProperty("id", is(START_SEQ)),
//                                hasProperty("name", is(USER.getName()))
//                        )
//                )));

    }

//    @Test
//    public void testDelete() throws Exception {
//
//    }
//
//    @Test
//    public void testEditForUpdate() throws Exception {
//
//    }
//
//    @Test
//    public void testEditForCreate() throws Exception {
//
//    }
//
//    @Test
//    public void testUpdateOrCreate() throws Exception {
//
//    }
//
//    @Test
//    public void testGetBetween() throws Exception {
//
//    }
}