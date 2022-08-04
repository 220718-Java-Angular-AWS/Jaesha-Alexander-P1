package com.consoles;

import java.util.Scanner;

public class UserMenu extends View{
    Scanner scanner;
    public UserMenu() {
        viewName = "UserMenu";
        viewManager = ViewManager.getViewManager();
    }

    public void renderView() {
        scanner = new Scanner(System.in);

        boolean waitingResponse = false;
        while (!waitingResponse)
        {
            System.out.println("============User Menu ============");
            System.out.println("(S) Submit request for reimbursement/n (C) Cancel pending request for reimbursement /n (V) View reimbursements /n (E) Edit pending requests for reimbursements /n (Q) Quit");
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
