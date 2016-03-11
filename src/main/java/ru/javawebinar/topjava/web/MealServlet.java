package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by bolshakov-as on 04.03.2016.
 */

public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(UserServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        if(id == null || id.isEmpty()){
            //ADD
            UserMealsUtil.addMeal(LocalDateTime.parse(request.getParameter("dateTime"))
                                    ,request.getParameter("description")
                                    ,Integer.valueOf(request.getParameter("calories")));
        }
        else {
            //Edit
            UserMeal userMeal = UserMealsUtil.getMealById(getId(request));
            userMeal.setCalories(Integer.valueOf(request.getParameter("calories")));
            userMeal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
            userMeal.setDescription(request.getParameter("description"));
        }

        response.sendRedirect("meals");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if(action == null){
            request.setAttribute("mealList", UserMealsUtil.getMeals());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
        else if(action.equals("delete")){
            UserMealsUtil.deleteMeal(getId(request));
            LOG.debug("Delete");
            request.setAttribute("mealList", UserMealsUtil.getMeals());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
        else if(action.equals("edit")){
            LOG.debug("Edit");
            request.setAttribute("meal", UserMealsUtil.getMealById(getId(request)));
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        }
        else if(action.equals("add")){
            LOG.debug("ADD");
            request.setAttribute("meal", new UserMeal(LocalDateTime.now(), "", 1000));
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        }

    }

    private int getId(HttpServletRequest request){
        return Integer.parseInt(request.getParameter("id"));
    }


}
