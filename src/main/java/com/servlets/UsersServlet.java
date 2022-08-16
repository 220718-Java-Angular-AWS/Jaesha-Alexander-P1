package com.servlets;

import com.pojos.Users;
import com.service.UserServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


public class UsersServlet extends HttpServlet {
    UserServices service;

    ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("User servlet initializing...");
        this.service = new UserServices();
        this.objectMapper = new ObjectMapper();

    }



    /*
    Goal to be able to readall users read a specific user and validate a user login within this method
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int responeseNumb = 200;

        // want to get all the parameters entered because based on the paarameters entered is what we will do
        String paramUserName = req.getParameter("user-name");
        String paramPassWord = req.getParameter("user-password");


        System.out.println("Made into DOGET ");
        // want to get all the users so call service to read all
        if(paramUserName == null && paramPassWord == null )
        {

            List<Users> userList = service.readAllUsers();
            String json = objectMapper.writeValueAsString(userList);
            resp.getWriter().println(json);

        }

        // used to get a specific user based on the username
        else if(paramUserName != null && paramPassWord == null)
        {
            System.out.println("MADE INTO CHECK USERNAME ");
            System.out.println("paUsername: " + paramUserName);
            Users returnUser = service.getUser(paramUserName);
            System.out.println("USER: " + returnUser);
            String json = objectMapper.writeValueAsString(returnUser);
            resp.getWriter().println(json);
        }

        // used to validate the login
        else
        {

            boolean loginValid = service.validateUserLogin(paramUserName, paramPassWord);

            if(!loginValid)
            {
                // MEANS LOGIN NOT CORRECT
                // do something not sure of what to do
                // but want to let know something is wrong
                // may even want to set current user from here
                responeseNumb = 500;

            }

        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(responeseNumb);
    }



    /*
    GOAL : to create a user but also check and make sure the user doesnt already exists in the database
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int responeseNumb = 200;

        System.out.println("MADE IT INTO POST ");
        // grabbing the info from the request
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while(buffer.ready())
        {
            stringBuilder.append(buffer.readLine());
        }
        String json = stringBuilder.toString();

        System.out.println("POST JSON RESULT : " + json);

        // creating user object and checking if it is a valid user info - meaning not in the system already
        Users newUser = objectMapper.readValue(json,Users.class);

        System.out.println("POST NEW USER : "+ newUser);
        boolean checkNewUser = service.validateUserSignUp(newUser.getUsername(), newUser.getPassword(), newUser.getEmail());

        // valid sign up
        if(checkNewUser)
        {
            System.out.println("MADE INTO IF STATEMENT FOR NEW USER ");
            service.save(newUser);
        }
        // not valid
        else
        {
            System.out.println("NOT INTO IF STATEMENT FOR NEW USER ");
            // NEED TO FIND BETTER WAY TO COMMUNICATE USER ALREADY EXISTS
            responeseNumb = 500;
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(responeseNumb);
        System.out.println("MADE IT PASS SENDING BACK RESPONSE CODE ");
    }

    /*
    GOAL: to update any existing users
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // grabbing the info from the request
        System.out.println("MADE IN DOPUT ");
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while (buffer.ready()) {
            stringBuilder.append(buffer.readLine());
        }

        String json = stringBuilder.toString();


        // creating user object and checking if it is a valid user info - meaning not in the system already
        Users updateUser = objectMapper.readValue(json, Users.class);

//        updateUser = service.getUser(updateUser.getUsername());
        //?? NOTE: maybe should check to see if user exists later  ??

        service.updateUser(updateUser);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    /*
    GOAL: delete a user from the table
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // grabbing the info from the request
        System.out.println("MADE IN DODELETE BREADCRUMB");
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while (buffer.ready()) {
            stringBuilder.append(buffer.readLine());
        }

        String json = stringBuilder.toString();


        // creating user object and checking if it is a valid user info - meaning not in the system already
        Users deleteUser = objectMapper.readValue(json, Users.class);
//        deleteUser = service.getUser(deleteUser.getUsername());

        System.out.println("BC2 : "+ deleteUser);
        //?? NOTE: maybe should check to see if user exists later  ??

        // actually need to delete everything from er table before can do this
        service.deleteUser(deleteUser);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
