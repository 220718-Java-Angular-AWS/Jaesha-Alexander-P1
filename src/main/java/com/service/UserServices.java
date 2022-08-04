package com.service;

import com.daos.UserDAO;
import com.pojos.Users;

import java.util.List;

// this is what interacts with the UserDao
public class UserServices {
    private UserDAO userDAO;

    public UserServices()
    {
        this.userDAO = new UserDAO();
    }


    public void save(Users user)
    {
        userDAO.create(user);
    }

    public Boolean validateUser(String username)
    {
        return userDAO.readValidateUser(username);
    }

    public Users getUser(String username)
    {
        return userDAO.readUser(username);
    }

    public List<Users> readAllUsers()
    {
        return userDAO.readAll();
    }

    public Boolean updateUser(Users user)
    {
        return userDAO.update(user);
    }

    public Boolean deleteUser(Users user)
    {
        return userDAO.delete(user);
    }





}
