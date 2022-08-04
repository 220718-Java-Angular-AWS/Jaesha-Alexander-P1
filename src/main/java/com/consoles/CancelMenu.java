package com.consoles;

import com.daos.ExpenseReimbursementsDAO;
import com.pojos.ExpenseReimbursements;
import com.pojos.Users;
import com.service.ExpenseReimbursementServices;
import com.service.UserServices;

import java.util.*;

public class CancelMenu extends View{
    Scanner scanner;
    private LoginMenu loginMenu;
    private ExpenseReimbursementServices expenseReimbursementServices;
    Users currentUser;


    public CancelMenu() {
        viewName = "CancelMenu";
        viewManager = ViewManager.getViewManager();
        currentUser = loginMenu.getCurrentUser();

    }

    public void renderView() {
        scanner = new Scanner(System.in);

        Map<Integer, ExpenseReimbursements> expKeyValues = new HashMap<Integer, ExpenseReimbursements>();

        // allows user to cancel pending
        System.out.println("================ Cancel Pending Reimbursement Request ========");

        // need to get users reimbursements
        List<ExpenseReimbursements> expenses = expenseReimbursementServices.readAllER();

        // List of Users reimbursement
        List<ExpenseReimbursements> currentUserEx = new ArrayList<ExpenseReimbursements>();

        // iterate through all the exp to find the ones with the current users username
        for(ExpenseReimbursements exp: expenses)
        {
            if(exp.getExpenseUsername() == currentUser.getUsername() && exp.getExpenseStatus() == "Pending")
            {
                currentUserEx.add(exp);
                expKeyValues.put( exp.getExpenseID(), exp);
                System.out.println(exp.toString());
            }
        }

        // give the user the option to cancel
        boolean waitingResponse = false;

        while(!waitingResponse)
        {
            System.out.println("Enter the ID number of pending request to cancel");
            System.out.println("Or (B) Back/n (Q) Quit");

            String response = scanner.nextLine();

            // response was an id for exp
            if(expKeyValues.containsKey(Integer.parseInt(response)))
            {
                System.out.println("Cancel Pending for ID: " +Integer.parseInt(response) );
                System.out.println("(Y) Yes (N) No ");
                String response2 = scanner.nextLine();

                boolean waitingResponse2 = false;

                while(!waitingResponse2)
                {
                    if(response2 == "Y" || response2 == "y")
                    {

                        waitingResponse2 = true;
                        // then cancel the pending
                        ExpenseReimbursements cancelExp = expKeyValues.get(Integer.parseInt(response));
                        cancelExp.setExpenseStatus("Cancelled");

                        expenseReimbursementServices.updateER(cancelExp);
                        // once done should prompt back to cancel menu screen


                    }
                    else if(response2 == "N" || response2 == "n")
                    {
                        // then go back by getting out of loop
                        waitingResponse2 = true;
                        continue;
                        // once done should prompt back to cancel menu screen
                    }

                    else
                    {
                        System.out.println("Invalid response try again");

                    }
                }

            } // end of inner while loop

            // response was other than exp ID number
            else if(response == "B" || response == "b")
            {
                waitingResponse = true;
                if(currentUser.getStatus() == "Admin")
                {
                    viewManager.navigate("AdminMenu");
                }
                else
                {
                    viewManager.navigate("UserMenu");
                }


            }
            else if(response == "Q" || response == "q")
            {
                waitingResponse = true;
                viewManager.quit();

            }
            else
            {
                System.out.println("Invalid Response Try Again ");
            }


        }








    }
}
