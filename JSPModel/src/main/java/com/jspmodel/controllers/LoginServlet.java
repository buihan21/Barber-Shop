package com.jspmodel.controllers;

import com.jspmodel.daos.LoginDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


//@WebServlet(name = "LoginSe", value = "/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String user = request.getParameter("username");
            String pass = request.getParameter("password");
            LoginDao loginDao = new LoginDao();
            boolean isValid = loginDao.checkLogin(user, pass);
            if (!isValid){
                response.sendRedirect("Login.jsp");
            } else {
                response.sendRedirect("Success.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}