package com;

import com.consoles.MainMenu;
import com.consoles.ViewManager;
import com.daos.ExpenseReimbursementsDAO;
import com.daos.UserDAO;
import com.pojos.ExpenseReimbursements;
import com.pojos.Users;
import com.service.ConnectionManagerService;

import java.sql.Connection;
import java.util.List;

public class Main {

    static void testingDAO() {
        UserDAO userDAO = new UserDAO();
        Users user1 = new Users("Sam", "Smith", "saSmith", "1234");
        Users user2 = new Users("Jaesha", "Alexander", "junie3432","1234", "Admin" );


            userDAO.create(user1);
            userDAO.create(user2);

        user1 = userDAO.readUser("saSmith");
        user2 = userDAO.readUser("junie3432");

    //        System.out.println("CREATE works");
            System.out.println(user1); // these dont have an id
            System.out.println(user2);

            // testing readValidate
            System.out.println("testing readValidate/n");
            System.out.println(userDAO.readValidateUser("junie3432"));
            System.out.println(userDAO.readValidateUser("saSmith"));
            System.out.println("readValidate works ");


            // testing readUser
            System.out.println("Testing read user");
            System.out.println(userDAO.readUser("junie3432"));
            System.out.println("read user complete ");


            // testing readAll
            List<Users> users =  userDAO.readAll();
            for( Users user : users)
            {
                System.out.println(user);
            }

            System.out.println("Read All Works");


            // testing update
            List<Users> users12 =  userDAO.readAll();
            for( Users user : users12)
            {
                System.out.println(user);
            }

            System.out.println("/ntesting update ");
            user1.setStatus("Admin");
            System.out.println("MAIN USER BEFORE UPDATE: " + user1+ "/n");

            userDAO.update(user1);
            List<Users> users10 =  userDAO.readAll();
            for( Users user55 : users10)
            {
                System.out.println(user55);
            }
            System.out.println("update works/n ");


            System.out.println("testing delete");
            userDAO.delete(user1);
            List<Users> users11 =  userDAO.readAll();
            for( Users user : users11)
            {
                System.out.println(user);
            }

        System.out.println("complete UserDAO ");


        // testing Expense ReimbursementsDAO
        ExpenseReimbursements er1 = new ExpenseReimbursements("junie3432", "08/04/2022", "practice", 30.52);
        ExpenseReimbursements er2 = new ExpenseReimbursements("junie3432", "08/08/2022", "practice 2", 3.52);
        ExpenseReimbursements er3 = new ExpenseReimbursements("saSmith", "08/09/2022", "practice 3", 23.76);
        ExpenseReimbursementsDAO erDAO = new ExpenseReimbursementsDAO();
    //
        // testing create
        System.out.println("testing ER create ");
        erDAO.create(er1);
        erDAO.create(er2);
        erDAO.create(er3);
        System.out.println("ER create complete /n ");

        er1 = erDAO.read(er1);
        er2 = erDAO.read(er2);
        er3= erDAO.read(er3);


        // testing read
        System.out.println("testing read ");
        ExpenseReimbursements erRead = erDAO.read(er1);
        System.out.println("/n" + erRead);
        System.out.println("ER read by index complete /n ");

        // testing readAll
        System.out.println("/nTesting read all");
        List<ExpenseReimbursements> erList =  erDAO.readAll();
        for( ExpenseReimbursements er : erList)
        {
            System.out.println(er);
        }
        System.out.println("Read All Works /n");

        // testing update
        System.out.println("/ntesting update ");
        er2.setExpenseDetails("changing details for update");
        erDAO.update(er2);
        List<ExpenseReimbursements> erList2 =  erDAO.readAll();
        int i = 0;
        for( ExpenseReimbursements er : erList2)
        {
            System.out.println(er + " " + i);
            i++;

        }
        System.out.println("update works /n");

        System.out.println("/ntesting delete");
        erDAO.delete(er1);
        List<ExpenseReimbursements> er4 =  erDAO.readAll();
        for( ExpenseReimbursements er : er4)
        {
            System.out.println(er);
        }

    }
    public static void main(String[] args) {



        System.out.println("Connecting...");
        Connection connection = ConnectionManagerService.getConnection();
        System.out.println("Done!");

//        testingDAO();


        //testing consoles
        ViewManager viewManager = ViewManager.getViewManager();

        viewManager.registerView(new MainMenu());

        // testing mainmenu
        viewManager.navigate("MainMenu");
        viewManager.navigate("SignUpMenu");
        viewManager.navigate("LoginMenu");
        viewManager.navigate("CancelMenu");
        viewManager.navigate("EditMenu");
        viewManager.navigate("SubmitMenu");
        viewManager.navigate("UserMenu");
        viewManager.navigate("ViewMenu");



        while(viewManager.isRunning())
        {
            viewManager.render();
        }












    }
}
