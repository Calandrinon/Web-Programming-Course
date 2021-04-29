package com.uppicvote.controller;

import com.uppicvote.repository.Repository;
import com.uppicvote.service.AuthenticationService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationController extends HttpServlet {
    private AuthenticationService service;

    public AuthenticationController() {
        this.service = new AuthenticationService(new Repository());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.println("The user " + request.getParameter("username") + " wants to log in...");
        if (this.service.authenticateUser(request.getParameter("username"), request.getParameter("password"))) {
            response.sendRedirect("/home");
        } else {
            printWriter.println("Bad username or password!");
        }
        printWriter.flush();
    }
}
