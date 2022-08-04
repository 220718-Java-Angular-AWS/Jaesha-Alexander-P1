package com.consoles;

import java.util.Scanner;

public class AdminMenu extends View{
    Scanner scanner;
    private LoginMenu loginMenu;

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
        // users menu
        scanner = new Scanner(System.in);

        boolean waitingResponse = false;
        while (!waitingResponse)
        {
            System.out.println("============Admin Menu ============");
            System.out.println("(S) Submit request for reimbursement/n (C) Cancel pending request for reimbursement /n (V) View reimbursements /n (E) Edit pending requests for reimbursements /n (A) Approve/Deny expense reimbursement/n (Q) Quit");
            String response = scanner.nextLine();

            if(response == "S" || response == "s")
            {
                waitingResponse = true;
                viewManager.navigate("SubmitMenu");

            }
            else if(response == "C" || response == "c")
            {
                waitingResponse = true;
                viewManager.navigate("CancelMenu");
            }
            else if(response == "V" || response == "v")
            {
                waitingResponse = true;
                viewManager.navigate("ViewMenu");
            }
            else if(response == "E" || response == "e")
            {
                waitingResponse = true;
                viewManager.navigate("EditMenu");
            }
            else if(response == "Q" || response == "q")
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
