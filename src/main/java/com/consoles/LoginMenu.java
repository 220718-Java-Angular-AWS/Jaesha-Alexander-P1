package com.consoles;

import com.pojos.Users;
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

    public void renderView() {
        scanner = new Scanner(System.in);

        System.out.println("================= Log In =================");
        System.out.println("Enter username: ");
        userName = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();

        // first check to see if the username  exists in the database

        boolean usernameExists = userServices.validateUser(userName);

        // Username exists
        if (usernameExists)
        {
            // need to get a user who has the username
            // then check to see if password is correct
            Users user = userServices.getUser(userName);

            // a successful login the strings match
            if(user.getPassword() == password)
            {
                // login successful
                System.out.println("Log in Successful");

                // check status and prompt to userMenu or adminMenu
                String userStatus = user.getStatus();
                if(userStatus == "User")
                {
                    currentUser = user;
                    viewManager.navigate("UserMenu");
                }

                else if(userStatus == "Admin")
                {
                    currentUser = user;
                    viewManager.navigate("AdminMenu");
                }

            }

            // password didnt match
            // will prompt user with option to try login again sign up or quit
            // will continue to prompt user if invalid response is given
            else
            {
                boolean waitingResponse = false;
                while (!waitingResponse)
                {
                    System.out.println("Password not correct ");
                    System.out.println("(T) Try log in again /n (M) Main Menu /n (Q) Quit ");
                    String response = scanner.nextLine();

                    if(response == "T" || response == "t")
                    {
                        waitingResponse = true;
                        viewManager.navigate("LoginMenu");
                    }
                    else if(response == "M" || response == "M")
                    {
                        waitingResponse = true;
                        viewManager.navigate("MainMenu");
                    }
                    else if(response == "Q" || response == "q")
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

        // username is not in the database
        else
        {
            // allowing the user to try to log in again or go to sign up or quit
            // will keep asking for a response if user does not input valid option
            System.out.println("Username does not exist ");
            boolean waitingResponse = false;

            while(!waitingResponse)
            {
                System.out.println("Password not correct ");
                System.out.println("(T) Try Log in Again /n (S) Sign Up /n (Q) Quit ");
                String response = scanner.nextLine();

                if(response == "T" || response == "t")
                {
                    waitingResponse = true;
                    viewManager.navigate("LoginMenu");
                }
                else if(response == "S" || response == "s")
                {
                    waitingResponse = true;
                    viewManager.navigate("SignUpMenu");
                }
                else if(response == "Q" || response == "q")
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

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }
}
