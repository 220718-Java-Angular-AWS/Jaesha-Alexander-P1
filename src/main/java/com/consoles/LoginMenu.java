package com.consoles;

import com.pojos.Users;
import com.service.CurrentUser;
import com.service.UserServices;

import java.util.Scanner;

public class LoginMenu extends View{
    private UserServices userServices;
    private Scanner scanner;
    private String userName;
    private String password;
    private Users currentUser;

    public LoginMenu() {
        viewName = "LoginMenu";
        viewManager = ViewManager.getViewManager();
    }

    public void renderView()
    {
        scanner = new Scanner(System.in);
        userServices = new UserServices();
        System.out.println("================= Log In =================");
        System.out.println("Enter username: ");
        userName = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();

        // first check to see if the username  exists in the database

        boolean validLogin = userServices.validateUserLogin(userName, password);

        // means that username and password are a match
        // successful login
        if (validLogin)
        {
            validLogin();
        }

        else
        {
            invalidLogin();
        }





    }

    public void validLogin()
    {
        Users user = userServices.getUser(userName);

        // setting the current user to be used by other classes
        CurrentUser.setCurrentUser(user);

        // check status and prompt to userMenu or adminMenu
        String userStatus = user.getStatus();
        if(userStatus.equals("User"))
        {

            viewManager.navigate("UserMenu");
        }

        else if(userStatus.equals("Admin"))
        {
            viewManager.navigate("AdminMenu");
        }

    }

    public void invalidLogin()
    {

        // allowing the user to try to log in again or go to sign up or quit
        // will keep asking for a response if user does not input valid option
        boolean waitingResponse = false;

        while(!waitingResponse)
        {
            System.out.println("Invalid Login ");
            System.out.println("(T) Try Log in Again /n (S) Sign Up /n (Q) Quit ");
            String response = scanner.nextLine();

            if(response.equals("T") || response.equals("t"))
            {
                waitingResponse = true;
                viewManager.navigate("LoginMenu");
            }
            else if(response.equals("S") || response.equals("s"))
            {
                waitingResponse = true;
                viewManager.navigate("SignUpMenu");
            }
            else if(response.equals("Q") || response.equals("q"))
            {
                waitingResponse = true;
                viewManager.quit();
            }
            else
            {
                System.out.println("Not a valid option try again");
            }
        }

    }

}
