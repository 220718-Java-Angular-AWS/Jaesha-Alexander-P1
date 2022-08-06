package com;


import com.consoles.*;
import com.daos.ExpenseReimbursementsDAO;
import com.daos.UserDAO;
import com.pojos.ExpenseReimbursements;
import com.pojos.Users;
import com.service.ConnectionManagerService;
import com.service.UserServices;

import java.sql.Connection;
import java.util.List;

public class Main {


    public static void main(String[] args) {
// STILL NEED TO FINISH TESTING consoles


        System.out.println("Connecting...");
        Connection connection = ConnectionManagerService.getConnection();
        System.out.println("Done!");

//        UserServices userServices = new UserServices();
//        UserDAO userDAO = new UserDAO();
//        Users user1 = new Users("jane", "jug", "jug1@gmail.com", "ju1","1234");
//        Users user2 = new Users("steve", "mu", "mu31@gmail.com", "mu31","12345");

//testing consoles
        ViewManager viewManager = ViewManager.getViewManager();

        viewManager.registerView(new MainMenu());
        viewManager.registerView(new AdminMenu());
        viewManager.registerView(new CancelMenu());
        viewManager.registerView(new EditMenu());
        viewManager.registerView(new LoginMenu());
        viewManager.registerView(new SignUpMenu());
        viewManager.registerView(new SubmitMenu());
        viewManager.registerView(new UserMenu());
        viewManager.registerView(new ApproveDenyMenu());

        System.out.println("1");
        viewManager.navigate("MainMenu");
        System.out.println("2");


        while(viewManager.isRunning())
        {
            viewManager.render();
        }

// TESTING USERSERVICES

/*
        // TESTING VALID SIGN UP
        boolean valueUser1 = userServices.validateUserSignUp(user1.getUsername(),user1.getEmail());
        System.out.println("USER 1 should be: False/n Result: " + valueUser1);

        boolean valueUser2 = userServices.validateUserSignUp(user2.getUsername(),user2.getEmail());
        System.out.println("USER 2 should be: True/n Result: " + valueUser2);

        // testing email portion
        boolean valueUser3 = userServices.validateUserSignUp("mi","ex@ui.s");
        System.out.println("should be: false/n Result: " + valueUser3);
        boolean valueUser4 = userServices.validateUserSignUp("mi","exui.sam");
        System.out.println("should be: false/n Result: " + valueUser4);

       // TESTING VALID LOGIN
        boolean value = userServices.validateUserLogin(user1.getUsername(), user1.getPassword());
        System.out.println("should be TRUE/n Result: " + value);
        boolean value2 = userServices.validateUserLogin(user1.getUsername(), "wrongPass");
        System.out.println("should be FALSE/n Result: " + value2);
/*


// TESTING DAO
        /*
        UserDAO userDAO = new UserDAO();
        Users user1 = new Users("Sam", "Smith","ex@gmail.com" ,"saSmith", "1234");
        Users user2 = new Users("Jaesha", "Alexander","ex1@hu.com" ,"junie3432","1234", "Admin" );


            userDAO.create(user1);
            userDAO.create(user2);

        user1 = userDAO.readUser("saSmith");
        user2 = userDAO.readUser("junie3432");

            System.out.println("CREATE works");
            System.out.println(user1); // these dont have an id
            System.out.println(user2);

        Users user1 = new Users("jane", "jug", "jug1@gmail.com", "ju1","1234");
        Users user2 = new Users("jane", "jug", "jug31@gmail.com", "ju31","1234");
        Users user3 = new Users("jane", "jug", "jug315@gmail.com", "ju1","1234");
        UserDAO userDAO = new UserDAO();
        userDAO.create(user1);
        user1 = userDAO.readUser(user1.getUsername());
        boolean value = userDAO.readValidateUser(user1.getUsername(), user1.getEmail());
        System.out.println(value);

        boolean value2 = userDAO.readValidateUser(user2.getUsername(), user2.getEmail());
        System.out.println(value2);

        boolean value3 = userDAO.readValidateUser(user3.getUsername(), user3.getEmail());
//        System.out.println(value3);


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
         */






















    }
}
