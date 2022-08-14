package com.servlets;

import com.pojos.ExpenseReimbursements;
import com.pojos.Users;
import com.service.ExpenseReimbursementServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.List;

public class ExpenseReimbursementServlet extends HttpServlet {
    ObjectMapper objectMapper;
    ExpenseReimbursementServices service;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Expense Reimbursement servlet initializing...");
        this.service = new ExpenseReimbursementServices();
        this.objectMapper = new ObjectMapper();

    }


    /*
    GOAL: to read one er or read all exp re based on if a paramter is read in (id ) or read all by user based on username
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramID = req.getParameter("exp-id");
        String paramUsername = req.getParameter("exp-username");
        if(paramID == null && paramUsername == null)
        {
            List<ExpenseReimbursements>  erAll = service.readAllER();
            String json = objectMapper.writeValueAsString(erAll);
            resp.getWriter().println(json);
        }
        else if(paramUsername != null && paramID == null)
        {
            List<ExpenseReimbursements> erByUser = service.readByUser(paramUsername);
            String json = objectMapper.writeValueAsString(erByUser);
            resp.getWriter().println(json);

        }
        else if (paramUsername == null && paramID != null)
        {
            ExpenseReimbursements er = service.readER(Integer.parseInt(paramID));
            String json = objectMapper.writeValueAsString(er);
            resp.getWriter().println(json);
        }
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while(buffer.ready())
        {
            stringBuilder.append(buffer.readLine());

        }
        String json = stringBuilder.toString();

        ExpenseReimbursements er = objectMapper.readValue(json, ExpenseReimbursements.class);
//        System.out.println(er);
        service.save(er);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPut(req, resp);
        // used for updating
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = req.getReader();

        while(bufferedReader.ready())
        {
            stringBuilder.append(bufferedReader.readLine());
        }
        String json = stringBuilder.toString();
        ExpenseReimbursements er = objectMapper.readValue(json, ExpenseReimbursements.class);
//        System.out.println(er);
        service.updateER(er);

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
        ExpenseReimbursements deleteER = objectMapper.readValue(json, ExpenseReimbursements.class);
//        deleteER = service.readER(deleteER.getExpenseID()); // MAY BE Unn


        service.deleteER(deleteER);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    public void destroy() {
        super.destroy();
    }


}