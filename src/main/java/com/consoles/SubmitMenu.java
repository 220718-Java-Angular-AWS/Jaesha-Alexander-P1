package com.consoles;

import com.pojos.ExpenseReimbursements;
import com.pojos.Users;
import com.service.CurrentUser;
import com.service.ExpenseReimbursementServices;

import java.util.Scanner;

public class SubmitMenu extends View{
    private Scanner scanner;

    static Users currentUser;

    private ExpenseReimbursementServices expenseReimbursementServices;

    public SubmitMenu() {
        viewName = "SubmitMenu";
        viewManager = ViewManager.getViewManager();

    }

    public void renderView() {
//        currentUser = CurrentUser.getCurrentUser();
//        scanner = new Scanner(System.in);
//        expenseReimbursementServices = new ExpenseReimbursementServices();
//
//
//        System.out.println("================ Submit Reimbursement Expense ==================");
//
//        System.out.println("Enter (B) Back or (Q) Quit at anytime");
//
//        System.out.println("Enter date: ");
//        String date = scanner.nextLine();
////        checkInput(date);
//        System.out.println("Choose (L) Lodging (F) Food (T) Travel ");
//        String option = scanner.nextLine();
////        checkInput(option);
//        if(option.equals("L"))
//        {
//            option = "Lodging";
//        }
//        if(option.equals("F"))
//        {
//            option = "Food";
//        }
//        if(option.equals("T"))
//        {
//            option = "Travel";
//        }
//        System.out.println("Enter details: ");
//        String details = scanner.nextLine();
////        checkInput(details);
//
//        System.out.println("Enter amount: ");
//        String amount = scanner.nextLine();
////        checkInput(amount);
//        ExpenseReimbursements expenseReimbursements = new ExpenseReimbursements(currentUser.getUsername(), date, option, details, Double.parseDouble(amount));
//        expenseReimbursements.setExpenseStatus("Pending");
//
//        expenseReimbursementServices.save(expenseReimbursements);
//
//        System.out.println("Expense Reimbursement Submitted ");
//
//        // check status and prompt to userMenu or adminMenu
//        String userStatus = currentUser.getStatus();
//        if(userStatus.equals("User"))
//        {
//
//            viewManager.navigate("UserMenu");
//        }
//
//        else if(userStatus.equals("Admin"))
//        {
//            viewManager.navigate("AdminMenu");
//        }
//    }
//
////    public void checkInput(String inp) {
////        if (inp.equals("B") || inp.equals("b")) {
////            if (currentUser.getStatus() == "Admin") {
////                viewManager.navigate("AdminMenu");
////            } else {
////                viewManager.navigate("UserMenu");
////            }
////
////        }
////
////        else if (inp.equals("Q") || inp.equals("q")) {
////            viewManager.quit();
////        }
    }

}
