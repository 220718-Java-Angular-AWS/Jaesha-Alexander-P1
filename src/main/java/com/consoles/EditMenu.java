package com.consoles;

import com.pojos.ExpenseReimbursements;
import com.pojos.Users;
import com.service.CurrentUser;
import com.service.ExpenseReimbursementServices;

import java.util.*;

public class EditMenu extends View {
    private Scanner scanner;
    static Users currentUser;
    private ExpenseReimbursementServices expenseReimbursementServices;
    private Map<Integer, ExpenseReimbursements> expKeyValues;

    public EditMenu() {
        viewManager = ViewManager.getViewManager();
        viewName = "EditMenu";
    }


    public void renderView() {
        currentUser = CurrentUser.getCurrentUser();
        scanner = new Scanner(System.in);
        expKeyValues = new HashMap<Integer, ExpenseReimbursements>();
        expenseReimbursementServices = new ExpenseReimbursementServices();

        editPending();
        waitingResponse();

    }

    private void waitingResponse() {
        // give the user the option to cancel
        boolean waitingResponse = false;

        while(!waitingResponse)
        {
            System.out.println("Enter the ID number of pending request to Edit");
            System.out.println("Or (B) Back/n (Q) Quit");

            String response = scanner.nextLine();

            // response was an id for exp
            if(expKeyValues.containsKey(Integer.parseInt(response)))
            {
                System.out.println("Edit Pending for ID: " +Integer.parseInt(response) );
                System.out.println("(Y) Yes (N) No ");
                String response2 = scanner.nextLine();

                boolean waitingResponse2 = false;

                while(!waitingResponse2)
                {
                    if(response2 == "Y" || response2 == "y")
                    {

                        waitingResponse2 = true;

                        ExpenseReimbursements editExp = expKeyValues.get(Integer.parseInt(response));

                        System.out.println("Edit Current Request");

                        System.out.println("Enter Date: ");
                        String newDate = scanner.nextLine();
                        System.out.println("Enter Details: ");
                        String newDetails = scanner.nextLine();
                        System.out.println("Enter amount: ");
                        double newAmount = Double.parseDouble(scanner.nextLine());

                        // updating exp values
                        editExp.setExpenseDate(newDate);
                        editExp.setExpenseDetails(newDetails);
                        editExp.setExpenseAmount(newAmount);

                        expenseReimbursementServices.updateER(editExp);


                        // once done should prompt back to edit menu screen


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
                if (currentUser.getStatus() == "Admin")
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

    private void editPending() {
        // allows user to edit pending
        System.out.println("================ Edit Pending Reimbursement Requests ========");

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

    }


}
