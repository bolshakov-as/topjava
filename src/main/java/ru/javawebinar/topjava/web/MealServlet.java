package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by bolshakov-as on 04.03.2016.
 */

public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(UserServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameterValues("but").length > 0 && request.getParameterValues("but")[0].equals("Delete")){
            UserMealsUtil.deleteMeal(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("mealList", UserMealsUtil.getMeals());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
        else if(request.getParameterValues("but").length > 0 && request.getParameterValues("but")[0].equals("Edit")){
            request.setAttribute("meal", UserMealsUtil.getMealById(Integer.parseInt(request.getParameter("id"))));
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        }
        else{
            request.setAttribute("mealList", UserMealsUtil.getMeals());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if(action == null){
        }
        else if(action.equals("edit")){
            LOG.debug("Edit");
        }
        else if(action.equals("delete")){
            LOG.debug("Delete");
        }
        else if(action.equals("add")){
            LOG.debug("ADD");
        }

        request.setAttribute("mealList", UserMealsUtil.getMeals());
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);

    }

    private int getId(HttpServletRequest request){
        return Integer.parseInt(request.getParameter("id"));
    }


}
