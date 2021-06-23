package com.exam.repository;

import com.exam.model.Asset;
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


    public Optional<User> getUserWithName(String username) {
        User user = null;
        ResultSet resultSet;

        try {
            resultSet = statement.executeQuery("SELECT * FROM Users WHERE username = '" + username + "'");
            if (resultSet.next())
                user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }


    public ArrayList<Asset> getUserAssets(int userId) {
        ArrayList<Asset> assets = new ArrayList<Asset>();
        ResultSet rs;
        try {
            rs = statement.executeQuery("select * from Assets where UserId="+userId);
            while (rs.next()) {
                Asset asset = new Asset(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                asset.setId(rs.getInt(1));
                assets.add(asset);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }


    public Optional<Asset> getAssetByName(String name) {
        Asset asset = null;
        ResultSet resultSet;

        try {
            resultSet = statement.executeQuery("SELECT * FROM Assets WHERE Name = '" + name + "'");
            if (resultSet.next()) {
                asset = new Asset(resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
                asset.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(asset);
    }


    public boolean addAsset(Asset asset, User user) {
        try {
            String query = "INSERT INTO Assets (UserId, Name, Description, Value) VALUES (" + user.getId() + ",'" + asset.getName() + "','" + asset.getDescription() + "'," + asset.getValue() + ");";
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateAsset(Asset asset) {
        int r = 0;
        try {
            r = statement.executeUpdate("update assets set description='"+asset.getDescription()+"', value="+asset.getValue() +
                    " where id="+asset.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (r>0) return true;
        else return false;
    }
}