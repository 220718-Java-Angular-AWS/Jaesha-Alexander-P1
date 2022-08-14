package com.service;

import com.pojos.CurrentUser;

public class CurrentUserService {
    private CurrentUserService currentUserService;

    public CurrentUserService()
    {
        this.currentUserService = new CurrentUserService();
    }

    // save
    public void saveCurrentUser(CurrentUser currentUser)
    {
        // need to delete the old current user
        deleteCurrentUser();

        currentUserService.saveCurrentUser(currentUser);

    }

    // read
    public CurrentUser readCurrentUser()
    {
        return currentUserService.readCurrentUser();
    }

    // update
    public void updateCurrentUser(CurrentUser currentUser)
    {
        currentUserService.updateCurrentUser(currentUser);
    }

    //delete
    public void deleteCurrentUser()
    {
        currentUserService.deleteCurrentUser();
    }

}
