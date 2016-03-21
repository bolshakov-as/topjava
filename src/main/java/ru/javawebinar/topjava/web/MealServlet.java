package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private ConfigurableApplicationContext appCtx;
    private UserMealRestController userMealRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        userMealRestController = appCtx.getBean(UserMealRestController.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("actionFilter") != null){

            String sStartTime = Objects.requireNonNull(request.getParameter("startTime"));
            String sEndTime = Objects.requireNonNull(request.getParameter("endTime"));
            String sStartDate = Objects.requireNonNull(request.getParameter("startDate"));
            String sEndDate = Objects.requireNonNull(request.getParameter("endDate"));

            LocalTime startTime = (sStartTime.isEmpty())? LocalTime.MIN: LocalTime.parse(sStartTime);
            LocalTime endTime = (sEndTime.isEmpty())? LocalTime.MAX: LocalTime.parse(sEndTime);
            LocalDate startDate = (sStartDate.isEmpty())? LocalDate.MIN: LocalDate.parse(sStartDate);
            LocalDate endDate = (sEndDate.isEmpty())? LocalDate.MAX: LocalDate.parse(sEndDate);

            // LocalTime.parse(startTime)
            LOG.info("get filtered meals");
            LOG.info("");
            request.setAttribute("mealList", userMealRestController.getFiltered(startDate, startTime, endDate, endTime));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
        else
        {
            String id = request.getParameter("id");
            UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));
            LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
            userMealRestController.save(userMeal);
            response.sendRedirect("meals");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList", userMealRestController.getAll());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            userMealRestController.delete(id);
            response.sendRedirect("meals");
        }
        else if(action.equals("setUser")){
            int id = getId(request);
            LOG.info("setUser {}", id);
            LoggedUser.setId(id);
            response.sendRedirect("meals");
        }
        else {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(LocalDateTime.now(), "", 1000) :
                    userMealRestController.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
