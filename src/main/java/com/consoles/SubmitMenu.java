package com.consoles;

import com.pojos.ExpenseReimbursements;
import com.pojos.Users;
import com.service.ExpenseReimbursementServices;

import java.util.Scanner;

public class SubmitMenu extends View{
    private Scanner scanner;

    private LoginMenu loginMenu;
    private Users currentUser;

    private ExpenseReimbursementServices expenseReimbursementServices;

    public SubmitMenu() {
        viewName = "SubmitMenu";
        viewManager = ViewManager.getViewManager();
        currentUser = loginMenu.getCurrentUser();
    }

    public void renderView() {
        System.out.println("================ Submit Reimbursement Expense ==================");

        System.out.println("Enter (B) Back or (Q) Quit at anytime");

        System.out.println("Enter date: ");
        String date = scanner.nextLine();
        checkInput(date);

        System.out.println("Enter details: ");
        String details = scanner.nextLine();
        checkInput(details);

        System.out.println("Enter amount: ");
        String amount = scanner.nextLine();
        checkInput(amount);

        ExpenseReimbursements expenseReimbursements = new ExpenseReimbursements(currentUser.getUsername(), date, details, Double.parseDouble(amount));
        expenseReimbursements.setExpenseStatus("Pending");

        expenseReimbursementServices.save(expenseReimbursements);

        viewManager.navigate("UserMenu");


    }

    public void checkInput(String inp)
    {
        if (inp == "B" || inp == "b")
        {
            if (currentUser.getStatus() == "Admin")
            {
                viewManager.navigate("AdminMenu");
            }
            else
            {
                viewManager.navigate("UserMenu");
            }

        }
        if(inp == "Q" || inp == "q")
        {
            viewManager.quit();
        }
    }
}
