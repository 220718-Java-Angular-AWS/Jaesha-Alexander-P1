package com.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojos.CurrentUser;
import com.pojos.ExpenseReimbursements;
import com.service.CurrentUserService;
import com.service.ExpenseReimbursementServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class CurrentUserServlet extends HttpServlet {
    ObjectMapper objectMapper;
    CurrentUserService service;

    @Override
    public void init() throws ServletException {
        super.init();
        this.service = new CurrentUserService();
        this.objectMapper = new ObjectMapper();
        System.out.println("Current User Initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // would like to get the current user info
        CurrentUser currentUser = service.readCurrentUser();

        String json = objectMapper.writeValueAsString(currentUser);
        resp.getWriter().println(json);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while(buffer.ready())
        {
            stringBuilder.append(buffer.readLine());

        }
        String json = stringBuilder.toString();

        CurrentUser currentUser = objectMapper.readValue(json, CurrentUser.class);
//        System.out.println(er);
        service.saveCurrentUser(currentUser);
        System.out.println("SET THE CURRENT USER ");
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while(buffer.ready())
        {
            stringBuilder.append(buffer.readLine());

        }
        String json = stringBuilder.toString();

        CurrentUser currentUser = objectMapper.readValue(json, CurrentUser.class);
//        System.out.println(er);
        service.updateCurrentUser(currentUser);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // grabbing the info from the request
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while (buffer.ready()) {
            stringBuilder.append(buffer.readLine());
        }

        String json = stringBuilder.toString();


        // creating user object and checking if it is a valid user info - meaning not in the system already
        CurrentUser currentUser = objectMapper.readValue(json, CurrentUser.class);
//        deleteER = service.readER(deleteER.getExpenseID()); // MAY BE Unn


        service.deleteCurrentUser();

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
