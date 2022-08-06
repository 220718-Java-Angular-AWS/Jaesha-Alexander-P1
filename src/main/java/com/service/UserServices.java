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

    public Users getUser(String username)
    {
        return userDAO.readUser(username);
    }

    public List<Users> readAllUsers()
    {
        return userDAO.readAll();
    }

    public void updateUser(Users user)
    {
         userDAO.update(user);
    }

    public void deleteUser(Users user)
    {
         userDAO.delete(user);
    }

    // validate user sign up
    // want to check to make sure username is unique
    // want to check if email is unique
    public boolean validateUserSignUp(String username, String email)
    {
        boolean validInfo = false;

        // want to see if username or useremail exists
        boolean validateInfo = userDAO.readValidateUser(username, email);

        // means username and email is not in the databse
        if(validateInfo == false)
        {
            // checking email to make sure "valid"
            if(email.contains("@"))
            {
                //looking at ending of email
                // ex: "ex1@gmail.com" will look at ".com"
                String validEmailEnding = email.substring(email.indexOf('@')+1);

                // checking to see if email contains "."
                if(validEmailEnding.contains("."))
                {
                    // now will look at ending to check if 3 letters
                    // ex: ".com" will look at "com"
                    validEmailEnding = validEmailEnding.substring(validEmailEnding.indexOf('.')+1);

                    if(validEmailEnding.length() == 3)
                    {
                        // will make sure the end is all letters
                        if(Character.isLetter(validEmailEnding.charAt(0)) &&
                                Character.isLetter(validEmailEnding.charAt(1)) &&
                                Character.isLetter(validEmailEnding.charAt(2)) )
                        {
                            validInfo = true;
                        }
                    }

                }
            }
        }

        return validInfo;
    }

    public boolean validateUserLogin(String username, String password)
    {
        boolean validLogin = false;

        Users user = userDAO.readUser(username);

        if(user != null && user.getPassword().equals(password))
        {
            // successful login
            validLogin = true;
        }
        return validLogin;
    }





}
