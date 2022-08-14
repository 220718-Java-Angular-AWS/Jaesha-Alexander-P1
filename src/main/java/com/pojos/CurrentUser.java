package com.pojos;

public class CurrentUser {
    private String currentUser;

    public CurrentUser() {
    }

    public CurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
