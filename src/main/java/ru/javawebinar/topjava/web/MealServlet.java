package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bolshakov-as on 04.03.2016.
 */

public class MealServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameterValues("but").length > 0 && request.getParameterValues("but")[0].equals("Delete")){
            UserMealsUtil.deleteMeal(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("meals", UserMealsUtil.getMeals());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
        else if(request.getParameterValues("but").length > 0 && request.getParameterValues("but")[0].equals("Edit")){
            request.setAttribute("meal", UserMealsUtil.getMealById(Integer.parseInt(request.getParameter("id"))));
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        }
        else{
            request.setAttribute("meals", UserMealsUtil.getMeals());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("meals", UserMealsUtil.getMeals());
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        //response.sendRedirect("mealList.jsp");
    }

}
