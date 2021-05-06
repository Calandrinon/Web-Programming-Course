package com.uppicvote.repository;

import com.uppicvote.model.Image;
import com.uppicvote.model.User;

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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/UpPicVote", "root", "");
            statement = con.createStatement();
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

    public ArrayList<Image> getUserImages(int userId) {
        ArrayList<Image> images = new ArrayList<Image>();
        ResultSet resultSet;

        try {
            resultSet = statement.executeQuery("SELECT * FROM Images WHERE UserId = " + userId);
            while (resultSet.next()) {
                images.add(new Image(resultSet.getInt(1), resultSet.getString(2)));
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
            r = statement.executeUpdate("UPDATE Images SET Votes=" + image.getNumberOfVotes() + " WHERE ImageId=" + image.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (r>0) return true;
        else return false;
    }

}
