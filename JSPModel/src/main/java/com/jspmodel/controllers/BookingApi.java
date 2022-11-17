//package com.jspmodel.controllers;
//
//import netscape.javascript.JSObject;
//import org.json.JSONObject;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet(name = "BookingApi", value = "/BookingApi")
//public class BookingApi extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");
//        String date = request.getParameter("Date");
//        String time = request.getParameter("Time");
//        int service = Integer.parseInt(request.getParameter("Service"));
//        int stylist = Integer.parseInt(request.getParameter("Stylist"));
//        HttpSession session = request.getSession();
//        PrintWriter out = response.getWriter();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("set_date", date);
//        jsonObject.put("set_hour", time);
//        jsonObject.put("service_id", service);
//        jsonObject.put("employee_id", stylist);
//        jsonObject
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}
