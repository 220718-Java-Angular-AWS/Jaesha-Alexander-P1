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

    private String email;
    UserServices userServices;

    private Users user;

    public SignUpMenu() {
        viewName = "SignUpMenu";
        viewManager = ViewManager.getViewManager();
    }
    
    public void renderView() {
        scanner = new Scanner(System.in);
        userServices = new UserServices();

        System.out.println("========== Sign Up ===========");

        System.out.println("please enter your first name:");
        firstName = scanner.nextLine();

        System.out.println("please enter your last name:");
        lastName = scanner.nextLine();

        System.out.println("please enter your email: ");
        email = scanner.nextLine();

        System.out.println("please enter your username:");
        userName = scanner.nextLine();

        System.out.println("please enter your password:");
        password = scanner.nextLine();

        // info needs to be validated
        // should be false indicating no user exists
        Boolean valid = userServices.validateUserSignUp(userName, password, email);



        if(valid == true) // no username exists good to create new user
        {
            user = new Users(firstName, lastName,email, userName,password);
            userServices.save(user);

            // to set the id
            user = userServices.getUser(user.getUsername());
            System.out.println("Successful Sign Up");

            // take them to login prompt
            viewManager.navigate("LoginMenu");
        }

        // user exists and they should sign in
        else if(valid == false)
        {
            notValidSignUp();
        }


    }

    public void notValidSignUp()
    {
        // will loop till they put in an response
        boolean waitingResponse = false;
        while(!waitingResponse)
        {
            System.out.println("This username or email already exists ");
            System.out.println("(T) Try Signing Up Again /n (L) Log in /n (Q) Quit ");
            System.out.println("Enter a response: ");
            String response = scanner.nextLine();

            if(response.equals("T") || response.equals("t"))
            {
                waitingResponse = true;
                viewManager.navigate("SignUpMenu");
            }
            else if(response.equals("L") || response.equals("l"))
            {
                waitingResponse = true;
                viewManager.navigate("LoginMenu");
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
