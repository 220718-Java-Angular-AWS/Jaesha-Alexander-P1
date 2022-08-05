package com.consoles;

import java.lang.reflect.Type;
import java.util.Scanner;

public class MainMenu extends View{
    Scanner scanner;

    public MainMenu()
    {
        viewName = "MainMenu";
        viewManager = ViewManager.getViewManager();
    }

    // what we want the user to see
    public void renderView() {
        scanner = new Scanner(System.in);

        System.out.println("============ Main Menu============");

        System.out.println("Enter the 'S' for sign up or 'L' for log in ");
        System.out.println("(S) Sign Up /n (L) Log in /n (Q) Quit");


        String inp = scanner.nextLine();


        // based on letter go to new console
        if(inp.equals("S") || inp.equals("s"))
        {
            System.out.println("MADE IT IN S");
            viewManager.navigate("SignUpMenu");
            System.out.println("S after viewManager ");


        }
        else if(inp.equals("L") || inp.equals("l") )
        {
            viewManager.navigate("LoginMenu");
        }
        else if(inp.equals("Q")  || inp.equals("q") )
        {
            viewManager.quit();
        }

        // didnt choose correct symbol
        else
        {
            System.out.println("Not a valid option try again");
            viewManager.navigate("MainMenu");
        }


    }
}
