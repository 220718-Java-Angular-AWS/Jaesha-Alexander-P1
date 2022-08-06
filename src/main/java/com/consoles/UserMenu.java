package com.consoles;

import com.pojos.Users;
import com.service.CurrentUser;

import java.util.Scanner;

public class UserMenu extends View{
    Scanner scanner;
    static Users currentUser;
    public UserMenu() {
        viewName = "UserMenu";
        viewManager = ViewManager.getViewManager();
    }



    public void renderView() {
        currentUser = CurrentUser.getCurrentUser();
        scanner = new Scanner(System.in);

        waitingResponse();



    }

    public void waitingResponse()
    {
        boolean waitingResponse = false;
        while (!waitingResponse)
        {
            System.out.println("============User Menu ============");
            System.out.println("(S) Submit request for reimbursement/n (C) Cancel pending request for reimbursement /n (V) View reimbursements /n (E) Edit pending requests for reimbursements /n (Q) Quit");
            String response = scanner.nextLine();

            if(response.equals("S") || response.equals("s"))
            {
                waitingResponse = true;
                viewManager.navigate("SubmitMenu");

            }
            else if(response.equals("C") || response.equals("c"))
            {
                waitingResponse = true;
                viewManager.navigate("CancelMenu");
            }
            else if(response.equals("V") || response.equals("v"))
            {
                waitingResponse = true;
                viewManager.navigate("ViewMenu");
            }
            else if(response.equals("E") || response.equals("e"))
            {
                waitingResponse = true;
                viewManager.navigate("EditMenu");
            }
            else if(response.equals("Q")|| response.equals("q"))
            {
                waitingResponse = true;
                viewManager.quit();
            }
            else
            {
                System.out.println(" Not a valid response");
                System.out.println("Try again ");
            }

        }

    }



}
