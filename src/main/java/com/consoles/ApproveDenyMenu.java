package com.consoles;

import com.pojos.ExpenseReimbursements;
import com.pojos.Users;
import com.service.CurrentUser;
import com.service.ExpenseReimbursementServices;
import com.service.UserServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApproveDenyMenu extends View {
    private Scanner scanner;
    private ExpenseReimbursementServices expenseReimbursementServices;
    private UserServices userServices;
    private Users currentUser;

    private List<ExpenseReimbursements> displayER;
    private int allERSize;

    // this menu should allow the admin to see all request and filter through the request and then update status of request

    public ApproveDenyMenu() {
        viewName = "ApproveDenyMenu";
        viewManager = ViewManager.getViewManager();
    }

    public void renderView() {
        currentUser = CurrentUser.getCurrentUser();
        scanner = new Scanner(System.in);
        expenseReimbursementServices = new ExpenseReimbursementServices();
        userServices = new UserServices();

        System.out.println("================= Expense Reimbursements================");

        displayER = expenseReimbursementServices.readAllER();
        allERSize = displayER.size();

        // display all the er on first showing
        for(ExpenseReimbursements er : displayER)
        {
            // dont want them to be able to approve there own
            // because they could steal
            if(!er.getExpenseUsername().equals(currentUser.getUsername()))
            {
                System.out.println(er);
            }

        }

        waitingResponse();

    }


    private void waitingResponse() {
        boolean waitingResponse = false;

        while(!waitingResponse)
        {
            System.out.println("View Options: (A) All Expense Reimbursements (P) Pending Expense Reimbursements (C) Cancelled Expense Reimbursements (AP) Approved Expense Reimbursements (D) Denied Expense Reimbursements ") ;
            System.out.println("Other Options: (E) Edit Expense Reimbursement Status (B) back (Q) Quit");
            String response = scanner.nextLine();

            if(response.equals("A") || response.equals("a") || response.equals("P") || response.equals("p") || response.equals("C") || response.equals("c")|| response.equals("AP") || response.equals("ap")|| response.equals("D") || response.equals("d"))
            {
                findListDisplay(response);
            }

            else if(response.equals("E") || response.equals("e"))
            {
//                waitingResponse = true;

                // should just navigate back to this window
                System.out.println("Enter ID number of expense reimbursement: ");
                String input = scanner.nextLine();


                ExpenseReimbursements choosenEr = null;
                for(ExpenseReimbursements er : displayER)
                {
                    if(er.getExpenseID() == Integer.parseInt(input))
                    {
                        choosenEr = er;
                    }
                }

                if(choosenEr != null)
                {
                    System.out.println("Change status to: (A) Approved (D) Denied");
                    String statusInput = scanner.nextLine();
                    String statusUpdate = "";

                    if(statusInput.equals("A") || statusInput.equals("a") || statusInput.equals("D") || statusInput.equals("d"))
                    {
                        if(statusInput.equals("A") || statusInput.equals("a"))
                        {
                            statusUpdate = "Approved";
                        }
                        else
                        {
                            statusUpdate = "Denied";
                        }
                        waitingResponse = true;
                        choosenEr.setExpenseStatus(statusUpdate);
                        expenseReimbursementServices.updateER(choosenEr);
                        viewManager.navigate("ApproveDenyMenu");
                    }

                }
                else
                {
                    System.out.println("Invalid Response Try Again");
                }

            }
            else if(response.equals("B") || response.equals("b"))
            {
                waitingResponse = true;
                viewManager.navigate("AdminMenu");
            }
            else if(response.equals("Q") || response.equals("q"))
            {
                waitingResponse = true;
                viewManager.quit();
            }
            else
            {
                System.out.println("Invalid Response Try Again");
            }
        }
    }


    public void findListDisplay(String response)
    {
        System.out.println("================= Expense Reimbursements================");
        String grab = "";

        displayER = expenseReimbursementServices.readAllER();
        List<ExpenseReimbursements> temp = new ArrayList<ExpenseReimbursements>();

        if(!(response.equals("A") || response.equals("a")))
        {
            // cancelled
            if(response.equals("C") || response.equals("c"))
            {
                grab = "Cancelled";
            }
            // pending
            if(response.equals("P") || response.equals("p"))
            {
                grab = "Pending";
            }
            //completed
            if(response.equals("AP") || response.equals("ap"))
            {
                grab = "Approved";


            }
            if(response.equals("D") || response.equals("d"))
            {
                grab = "Denied";
            }

            for(ExpenseReimbursements er : displayER )
            {
                if(er.getExpenseStatus().equals(grab))
                {
                    System.out.println(er);
                    temp.add(er);
                }
            }

            displayER = temp;
        }


        else
        {
            for(ExpenseReimbursements er : displayER )
            {
                System.out.println(er);
            }
        }

    }


}

