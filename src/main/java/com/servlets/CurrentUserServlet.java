package com.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojos.CurrentUser;
import com.service.CurrentUserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class CurrentUserServlet extends HttpServlet {
    ObjectMapper objectMapper;
    CurrentUserService service;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Current User  servlet initializing.1..");
        this.service = new CurrentUserService();
        System.out.println("Current User  servlet initializing.2..");
        this.objectMapper = new ObjectMapper();
        System.out.println("Current User  servlet initializing.3..");

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
        System.out.println("MADE INTO SERVLET CURRENT USER ");
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
