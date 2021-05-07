package com.uppicvote.controller;

import com.uppicvote.model.Image;
import com.uppicvote.repository.Repository;
import com.uppicvote.service.AuthenticationService;
import com.uppicvote.service.ImageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class VotingController extends HttpServlet {
    private ImageService imageService;
    private AuthenticationService authenticationService;

    public VotingController() {
        this.imageService = new ImageService(new Repository());
        this.authenticationService = new AuthenticationService(new Repository());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            String filename = request.getParameter("filename");
            String[] tokens = filename.split("_");
            String posterUsername = tokens[0];
            Integer posterId = this.authenticationService.getUserId(posterUsername);
            Integer userId = this.authenticationService.getUserId(username);

            System.out.println("UserId: " + userId + "; PosterId: " + posterId);
            if (userId == posterId) {
                System.out.println("You can't rate your own posts...");
                return;
            }

            List<Image> userImages = this.imageService.getUserImages(posterId);
            List<Image> filteredUserImages = userImages.stream()
                    .filter(image -> image.getFilename().equals(filename))
                    .collect(Collectors.toList());
            if (filteredUserImages.size() > 0) {
                Image image = filteredUserImages.get(0);
                image.incrementNumberOfVotes();
                System.out.println("The action is: " + request.getParameter("action"));
                if (request.getParameter("action").equals("upvote")) {
                    this.imageService.upvoteImage(image, this.authenticationService.getUserId(username));
                    System.out.println("Image '" + filename + "' was upvoted.");
                } else {
                    this.imageService.downvoteImage(image, this.authenticationService.getUserId(username));
                    System.out.println("Image '" + filename + "' was downvoted.");
                }
            } else {
                System.out.println("DEBUG: Error raised while trying to rate an image.");
            }
        }
    }
}
