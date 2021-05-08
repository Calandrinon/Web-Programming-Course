package com.uppicvote.service;

import com.google.gson.Gson;
import com.uppicvote.model.BasicResponse;
import com.uppicvote.model.Image;
import com.uppicvote.repository.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class VotingService {
    private final ImageService imageService;
    private final AuthenticationService authenticationService;

    public VotingService(Repository repository) {
        this.imageService = new ImageService(repository);
        this.authenticationService = new AuthenticationService(repository);
    }

    public String votePicture(HttpServletRequest request, String username) {
        String filename = request.getParameter("filename");
        String[] tokens = filename.split("_");
        BasicResponse basicResponse = new BasicResponse();
        String posterUsername = tokens[0];

        Integer posterId = this.authenticationService.getUserId(posterUsername);
        Integer userId = this.authenticationService.getUserId(username);

        System.out.println("UserId: " + userId + "; PosterId: " + posterId);
        if (userId.equals(posterId)) {
            System.out.println("You can't rate your own posts...");
            basicResponse.setMessage("You can't rate your own posts...");
            basicResponse.setResponseCode(400);
            return new Gson().toJson(basicResponse);
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
                this.buildResponseMessageAfterUpvote(basicResponse, image, username);
            } else {
                this.buildResponseMessageAfterDownvote(basicResponse, image, username);
            }
        } else {
            System.out.println("For some reason, the image hasn't been found in the database. This is an issue with the filename.");
            basicResponse.setMessage("For some reason, the image hasn't been found in the database. This is an issue with the filename.");
            basicResponse.setResponseCode(404);
        }

        return new Gson().toJson(basicResponse);
    }


    private BasicResponse buildResponseMessageAfterUpvote(BasicResponse basicResponse, Image image, String username) {
        boolean votePermission = this.imageService.upvoteImage(image, this.authenticationService.getUserId(username));
        if (votePermission) {
            basicResponse.setMessage("Image was successfully upvoted.");
            basicResponse.setResponseCode(200);
        } else {
            basicResponse.setMessage("You have already rated this post.");
            basicResponse.setResponseCode(400);
        }

        return basicResponse;
    }

    private BasicResponse buildResponseMessageAfterDownvote(BasicResponse basicResponse, Image image, String username) {
        boolean votePermission = this.imageService.downvoteImage(image, this.authenticationService.getUserId(username));
        if (votePermission) {
            basicResponse.setMessage("Image was successfully downvoted.");
            basicResponse.setResponseCode(200);
        } else {
            basicResponse.setMessage("You have already rated this post.");
            basicResponse.setResponseCode(400);
        }

        return basicResponse;
    }
}
