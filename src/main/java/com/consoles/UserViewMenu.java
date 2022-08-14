package com.consoles;

import com.pojos.ExpenseReimbursements;
import com.pojos.Users;
import com.service.ExpenseReimbursementServices;

import java.util.List;
import java.util.Scanner;

public class UserViewMenu extends View{
    private Scanner scanner;
    private ExpenseReimbursementServices expenseReimbursementServices;
    private Users currentUser;

    public UserViewMenu() {
        viewName = "UserViewMenu";
        viewManager = ViewManager.getViewManager();
    }

    public void renderView() {
        scanner = new Scanner(System.in);
        expenseReimbursementServices = new ExpenseReimbursementServices();
//        currentUser = CurrentUser.getCurrentUser();

        System.out.println("============== All Reimbursements =============");
        List<ExpenseReimbursements> allER = expenseReimbursementServices.readAllER();

        for(ExpenseReimbursements er : allER)
        {
            if(er.getExpenseUsername().equals(currentUser.getUsername()))
            {
                System.out.println(er);
            }
        }


        boolean waitingResponse = false;

        while(!waitingResponse)
        {
            System.out.println("(B) Back (Q) QUit");
            String response = scanner.nextLine();

            if(response.equals("B") || response.equals("b"))
            {
                waitingResponse = true;


                // check status and prompt to userMenu or adminMenu
                String userStatus = currentUser.getStatus();
                if(userStatus.equals("User"))
                {

                    viewManager.navigate("UserMenu");
                }

                else if(userStatus.equals("Admin"))
                {
                    viewManager.navigate("AdminMenu");
                }
            }
            else if(response.equals("Q") || response.equals("q"))
            {
                viewManager.quit();
            }
            else
            {
                System.out.println("Invalid Response Try Again");
            }
        }




    }
}
