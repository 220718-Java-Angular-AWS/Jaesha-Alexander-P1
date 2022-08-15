package com.service;

import com.daos.CurrentUserDAO;
import com.pojos.CurrentUser;

public class CurrentUserService {
    private CurrentUserDAO currentUserDAO;

    public CurrentUserService()
    {
        this.currentUserDAO = new CurrentUserDAO();
    }

    // save
    public void saveCurrentUser(CurrentUser currentUser)
    {
        // need to delete the old current user
        deleteCurrentUser();

        currentUserDAO.create(currentUser);

    }

    // read
    public CurrentUser readCurrentUser()
    {
        return currentUserDAO.readAll();
    }

    // update
    public void updateCurrentUser(CurrentUser currentUser)
    {

        currentUserDAO.update(currentUser);
    }

    //delete
    public void deleteCurrentUser()
    {
        currentUserDAO.delete();
    }

}
