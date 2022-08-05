package com.consoles;

import com.pojos.Users;
import com.service.UserServices;

import java.util.Scanner;

public class SignUpMenu extends View{
    Scanner scanner;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private UserServices userServices;

    private Users user;

    public SignUpMenu() {
        viewName = "SignUpMenu";
        viewManager = ViewManager.getViewManager();
    }
    
    public void renderView() {
        scanner = new Scanner(System.in);

        System.out.println("========== Sign Up ===========");

        System.out.println("please enter your first name:");
        firstName = scanner.nextLine();

        System.out.println("please enter your last name:");
        lastName = scanner.nextLine();


        System.out.println("please enter your username:");
        userName = scanner.nextLine();

        System.out.println("please enter your password:");
        password = scanner.nextLine();

        // info needs to be validated
        // should be false indicating no user exists
        Boolean validUsername = userServices.validateUser(userName);

        if(validUsername == false) // no username exists good to create new user
        {
            user = new Users(firstName, lastName,userName,password);
            userServices.save(user);
            System.out.println("Successful Sign Up");

            // take them to login prompt
            viewManager.navigate("LoginMenu");
        }
        else if(validUsername == true) // user exists and they should sign in
        {
            // will loop till they put in an response
            boolean waitingResponse = false;
            while(!waitingResponse)
            {
                System.out.println("This username already exists ");
                System.out.println("(T) Try Signing Up Again /n (L) Log in /n (Q) Quit ");
                String response = scanner.nextLine();

                if(response == "T" || response == "t")
                {
                    waitingResponse = true;
                    viewManager.navigate("SignUpMenu");
                }
                else if(response == "L" || response == "L")
                {
                    waitingResponse = true;
                    viewManager.navigate("LoginMenu");
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
}
