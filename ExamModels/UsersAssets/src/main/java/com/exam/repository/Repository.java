package com.exam.repository;

import com.exam.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class Repository {
    private Statement statement;

    public Repository() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/JSPDatabase", "root", "");
            statement = con.createStatement();
            System.out.println("Successfully connected to the database!");
        } catch(Exception ex) {
            System.out.println("Connection error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Optional<User> authenticate(String username, String password) {
        ResultSet rs;
        User user = null;
        System.out.println(username+" "+password);

        try {
            rs = statement.executeQuery("SELECT * FROM Users WHERE Username='"+username+"' AND Password='"+password+"'");
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    /**
    public ArrayList<Image> getUserImages(int userId) {
        ArrayList<Image> images = new ArrayList<Image>();
        ResultSet resultSet;

        try {
            resultSet = statement.executeQuery("SELECT * FROM Images WHERE UserId = " + userId);
            while (resultSet.next()) {
                Image image = new Image(resultSet.getString(4), resultSet.getString(3));
                image.setId(resultSet.getInt(1));
                image.setUserId(resultSet.getInt(2));
                image.setNumberOfVotes(resultSet.getInt(5));
                images.add(image);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    public ArrayList<Image> getAllImages() {
        ArrayList<Image> images = new ArrayList<Image>();
        ResultSet resultSet;

        try {
            resultSet = statement.executeQuery("SELECT * FROM Images");
            while (resultSet.next()) {
                Image image = new Image(resultSet.getString(4), resultSet.getString(3));
                image.setId(resultSet.getInt(1));
                image.setUserId(resultSet.getInt(2));
                image.setNumberOfVotes(resultSet.getInt(5));
                images.add(image);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    public boolean updateImage(Image image) {
        int r = 0;
        try {
            r = statement.executeUpdate("UPDATE Images SET NumberOfVotes=" + image.getNumberOfVotes() + " WHERE ImageId=" + image.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (r>0) return true;
        else return false;
    }


    public boolean modifyVote(Image image, Integer userId, Integer point) {
        ResultSet resultSet;

        try {
            resultSet = statement.executeQuery("SELECT * FROM Images_Users WHERE UserId = " + userId + " AND ImageId=" + image.getId());

            System.out.println("IN THE REPOSITORY:");
            if (resultSet.next()) {
                System.out.println("Looks like the user has already rated this image.");
                return false;
            } else {
                System.out.println("The user hasn't yet rated this image.");
                String sqlStatement = "UPDATE Images SET NumberOfVotes = NumberOfVotes + (" + point + ")" + " WHERE ImageId = " + image.getId();
                statement.executeUpdate(sqlStatement);
                sqlStatement = "INSERT INTO Images_Users (ImageId, UserId) VALUES (" + image.getId() + "," + userId + ")";
                statement.executeUpdate(sqlStatement);
            }

            resultSet.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public Optional<Image> saveImage(Image image) {
        ResultSet resultSet;

        try {
            String sqlStatement = "INSERT INTO Images (UserId, Description, Filename, NumberOfVotes) VALUES (" + image.getUserId() + ", '" + image.getDescription() + "','" + image.getFilename() + "'," + image.getNumberOfVotes() + ")";
            System.out.println("<REPOSITORY> The inserted image: " + sqlStatement);
            statement.executeUpdate(sqlStatement);
            return Optional.of(image);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Integer getUserId(String username) {
        ResultSet resultSet;
        Integer userId = null;

        try {
            resultSet = statement.executeQuery("SELECT ID FROM Users WHERE Username = '" + username + "'");

            while (resultSet.next()) {
                userId = resultSet.getInt(1);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public ArrayList<Image> getTheTopNImages(Integer topN) {
        ArrayList<Image> images = new ArrayList<Image>();
        ResultSet resultSet;

        try {
            resultSet = statement.executeQuery("SELECT * FROM Images ORDER BY NumberOfVotes DESC LIMIT " + topN);
            while (resultSet.next()) {
                Image image = new Image(resultSet.getString(4), resultSet.getString(3));
                image.setId(resultSet.getInt(1));
                image.setUserId(resultSet.getInt(2));
                image.setNumberOfVotes(resultSet.getInt(5));
                images.add(image);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }
     **/
}