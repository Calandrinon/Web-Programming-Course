package com.uppicvote.controller;

import com.google.gson.Gson;
import com.uppicvote.model.BasicResponse;
import com.uppicvote.model.Image;
import com.uppicvote.repository.Repository;
import com.uppicvote.service.AuthenticationService;
import com.uppicvote.service.ImageService;
import com.uppicvote.service.VotingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class VotingController extends HttpServlet {
    private ImageService imageService;
    private AuthenticationService authenticationService;
    private VotingService votingService;

    public VotingController() {
        this.votingService = new VotingService(new Repository());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            PrintWriter out = response.getWriter();
            String jsonResponse = this.votingService.votePicture(request, username);
            response.setContentType("application/json");
            out.print(jsonResponse);
            out.flush();
        }
    }

}
