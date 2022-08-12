package com.consoles;

import com.pojos.Users;
import com.service.CurrentUser;

import java.util.Scanner;

public class AdminMenu extends View{
    Scanner scanner;


    static Users currentUser;

    /*
    NOT DONE NEED TO FIGURE HOW TO EDIT STILL
    // IDEA FOR VIEWING GIVE THEM AN OPTION TO VIEW ALL REIMBURSEMENTS AND THEN GIVE OPTION TO VIEW BASED ON PENDING COMPLETED OR ALL THEN BE ABLE TO APPROVE / DENY ON THE SAME PAGE
     */


    public AdminMenu()
    {
        viewName = "AdminMenu";
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
            System.out.println("============Admin Menu ============");
            System.out.println("(S) Submit request for reimbursement/n (C) Cancel pending request for reimbursement /n (V) View reimbursements /n (E) Edit pending requests for reimbursements /n (F) Filter expense reimbursement /n (Q) Quit");
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
                viewManager.navigate("UserViewMenu");
            }
            else if(response.equals("E") || response.equals("e"))
            {
                waitingResponse = true;
                viewManager.navigate("EditMenu");
            }
            else if(response.equals("F") || response.equals("F"))
            {
                waitingResponse = true;
                viewManager.navigate("ApproveDenyMenu");
            }

            else if(response.equals("Q") || response.equals("q"))
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
