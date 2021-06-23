package com.exam.controller;

import com.exam.model.Asset;
import com.exam.model.User;
import com.exam.repository.Repository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AssetController extends HttpServlet {
    private Repository repository;

    public AssetController() {
        this.repository = new Repository();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        User user = this.repository.getUserWithName(username).get();
        PrintWriter out = response.getWriter();

        if (user == null) {
            out.println("<h1> The user " + username + " does not exist. </h1>");
        } else {
            List<Asset> assetList = this.repository.getUserAssets(user.getId());
            String jsonResponse = new Gson().toJson(assetList);
            response.setContentType("application/json");
            out.print(jsonResponse);
            out.flush();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        User user = this.repository.getUserWithName(username).get();

        PrintWriter out = response.getWriter();

        if (user == null) {
            out.println("<h1> The user " + username + " does not exist. </h1>");
        } else {
            String assetName = request.getParameter("name");
            String description = request.getParameter("description");
            Integer value = Integer.parseInt(request.getParameter("value"));

            Asset asset = new Asset(user.getId(), assetName, description, value);
            this.repository.addAsset(asset, user);
            asset = this.repository.getAssetByName(assetName).get();

            String jsonResponse = new Gson().toJson(asset);
            response.setContentType("application/json");
            out.print(jsonResponse);
            out.flush();
        }
    }
}
