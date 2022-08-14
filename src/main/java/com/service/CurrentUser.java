package com.service;

import com.pojos.Users;

public class CurrentUser {


    private static Users currentUser;

    public static Users getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Users currentUser) {
        CurrentUser.currentUser = currentUser;
    }
}