package com.uppicvote.controller;

import com.google.gson.Gson;
import com.uppicvote.model.BasicResponse;
import com.uppicvote.model.Image;
import com.uppicvote.model.ImagesDto;
import com.uppicvote.repository.Repository;
import com.uppicvote.service.AuthenticationService;
import com.uppicvote.service.ImageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FilterController extends HttpServlet {
    private ImageService imageService;
    private AuthenticationService authenticationService;

    public FilterController() {
        this.imageService = new ImageService(new Repository());
        this.authenticationService = new AuthenticationService(new Repository());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestTopNParameter = (String)request.getParameter("topN");
        System.out.println("REQUEST TOP N PARAMETER AS STRING: " + requestTopNParameter);
        System.out.println("REQUEST TOP N PARAMETER AS OBJECT: " + request.getParameter("topN"));
        List<Image> images = null;
        Integer topN = null;

        if (requestTopNParameter == null) {
            images = this.imageService.getAllImages();
            topN = images.size();
        } else {
            topN = Integer.parseInt(requestTopNParameter);
            images = this.imageService.getTheTopNImages(topN);

            ImagesDto imagesDto = new ImagesDto(images);

            PrintWriter out = response.getWriter();
            String jsonResponse = new Gson().toJson(imagesDto);
            response.setContentType("application/json");
            out.print(jsonResponse);
            out.flush();
        }
    }
}
