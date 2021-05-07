package com.uppicvote.controller;

import com.uppicvote.repository.Repository;
import com.uppicvote.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
public class AuthenticationController extends HttpServlet {
    private AuthenticationService service;

    public AuthenticationController() {
        super();
        this.service = new AuthenticationService(new Repository());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter printWriter = response.getWriter();
        //printWriter.println("The user " + username + ":" + password + " wants to log in...");
        if (this.service.authenticateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("/index.jsp");
        } else {
            printWriter.println("Bad username or password!");
        }
        printWriter.flush();
    }
}
