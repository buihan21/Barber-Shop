package com.jspmodel.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //lấy về session hiện tại
            HttpSession session = request.getSession();

            //huỷ session
            session.invalidate();

            //quay về form đăng nhập
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/Login.jsp");
            dispatch.forward(request, response);
        } finally {
            out.close();
        }
    }
}
