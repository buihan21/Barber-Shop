//package com.jspmodel.controllers;
//
//import com.jspmodel.daos.LoginDao;
//
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
//
//@WebServlet(name = "LoginServlet", value = {"/Login"})
//public class LoginServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("text/html;charset=UTF-8");
//        try {
//            String user = request.getReader("username");
//            String pass = request.getParameter("password");
//            //LoginDao loginDao = new LoginDao();
//            boolean isValid = LoginDao.checkLogin(user, pass);
//            if (!isValid){
//                System.out.println("Dang Nhap Lai");
//                //response.sendRedirect("Success.jsp");
//                PrintWriter out = response.getWriter();
//
//            } else {
//                System.out.println("Dang Nhap Thanh Cong");
//                response.sendRedirect("Success.jsp");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}