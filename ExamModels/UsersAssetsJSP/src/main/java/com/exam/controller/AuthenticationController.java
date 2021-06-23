package com.exam.controller;

import com.exam.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationController extends HttpServlet {
    private Repository repository;

    public AuthenticationController() {
        this.repository = new Repository();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = new PrintWriter(response.getOutputStream());
        printWriter.println("<h1>Hello from the ExampleController!</h1>");
        printWriter.flush();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter printWriter = response.getWriter();

        if (this.repository.authenticate(username, password).isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("/menu.jsp");
        } else {
            printWriter.println("Bad username or password!");
        }
    }
}
