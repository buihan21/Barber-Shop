//package com.jspmodel.controllers;
//
//import com.jspmodel.daos.BookingDao;
//import com.jspmodel.models.Booking;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.sql.Date;
//import java.sql.Time;
//
//@WebServlet(name = "BookingServlet", value = "/BookingServlet")
//public class BookingServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");
//        String date = request.getParameter("Date");
//        String time = request.getParameter("Time");
//        int service = Integer.parseInt(request.getParameter("Service"));
//        int stylist = Integer.parseInt(request.getParameter("Stylist")); // mục DEMO ở front end(trong Appointment)
//        String phone = request.getParameter("Phone");
//        if (!date.equals("") && !time.equals("") && !service.equals("") && ){
//            Booking tmp = new Booking(service, stylist, Time.valueOf(time), Date.valueOf(date));
//            if (BookingDao.checkBooking(tmp)){
//
//                BookingDao.insertBooking(tmp);
//            }
//            else {
//                response
//            }
//        }
//    }
//}


package com.jspmodel.controllers;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jspmodel.models.Booking;
import com.jspmodel.daos.BookingDao;
import org.json.JSONObject;

import static com.jspmodel.daos.BookingDao.checkBooking;
import static com.jspmodel.daos.BookingDao.insertBooking;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.codehaus.jettison.json.JSONObject;

@WebServlet(name = "BookingServlet", value = "/BookingServlet")
public class BookingServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String requestUrl = request.getRequestURI();
        int name = Integer.parseInt(requestUrl.substring("/Booking/".length()));

        Booking booking = new Booking();
        boolean status = false;
        try {
            //booking = BookingDao.checkBooking(name);
            status = BookingDao.checkBooking(booking);
        } catch (Exception ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (status == true) {
            String json = "{\n";
            json += "\"user_id\": " + JSONObject.quote(Integer.toString(booking.getUser_id())) + ",\n";
            json += "\"service_id\": " + JSONObject.quote(Integer.toString(booking.getService_id())) + ",\n";
            json += "\"employee_id\": " + JSONObject.quote(Integer.toString(booking.getEmployee_id())) + ",\n";
            json += "\"set_hour\": " + JSONObject.quote(String.valueOf((Time) booking.getSet_hour())) + ",\n";
            json += "\"set_date\": " + JSONObject.quote(String.valueOf((Date) booking.getSet_date())) + ",\n";
//            json += "\"note\": " + JSONObject.quote(booking.getNote()) + ",\n";

            json += "}";
            response.getOutputStream().println(json);
        } else {
            //That person wasn't found, so return an empty JSON object. We could also return an error.
            response.getOutputStream().println("{}");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int service_id = Integer.parseInt(request.getParameter("service_id"));
        int employee_id = Integer.parseInt(request.getParameter("employee_id"));
        Time set_hour = Time.valueOf(request.getParameter("set_hour"));
        Date set_date = Date.valueOf(request.getParameter("set_date"));
        try {
            int a = insertBooking(new Booking(user_id,service_id,employee_id,set_hour,set_date));
        } catch (SQLException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}