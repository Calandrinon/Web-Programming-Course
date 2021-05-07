package com.uppicvote.service;

import com.uppicvote.repository.Repository;

import java.util.Optional;


public class AuthenticationService {
    private Repository repository;

    public AuthenticationService(Repository repository) {
        this.repository = repository;
    }

    public boolean authenticateUser(String username, String password) {
        return this.repository.authenticate(username, password).isPresent();
    }

    public Integer getUserId(String username) {
        return this.repository.getUserId(username);
    }
}
