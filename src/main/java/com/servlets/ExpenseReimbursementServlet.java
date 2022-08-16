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
        System.out.println("MADE INTO GET ER ");
        System.out.println(1);
        String paramID = req.getParameter("exp-id");
        System.out.println(2);
        String paramUsername = req.getParameter("exp-username");
        System.out.println(3);
        if(paramID == null && paramUsername == null)
        {
            System.out.println(4);
            List<ExpenseReimbursements>  erAll = service.readAllER();
            String json = objectMapper.writeValueAsString(erAll);
            resp.getWriter().println(json);
        }
        else if(paramUsername != null && paramID == null)
        {
            System.out.println(5);
            List<ExpenseReimbursements> erByUser = service.readByUser(paramUsername);
            System.out.println(5.1);
            String json = objectMapper.writeValueAsString(erByUser);
            System.out.println(5.2);
            resp.getWriter().println(json);
            System.out.println(5.3);

        }
        else if (paramUsername == null && paramID != null)
        {
            System.out.println(6);
            ExpenseReimbursements er = service.readER(Integer.parseInt(paramID));
            String json = objectMapper.writeValueAsString(er);
            resp.getWriter().println(json);
            System.out.println(7);
        }
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int numb = 500;
        StringBuilder stringBuilder = new StringBuilder();
//        System.out.println(2);
        BufferedReader buffer = req.getReader();
//        System.out.println(3);

        while(buffer.ready())
        {
//            System.out.println(4);
            stringBuilder.append(buffer.readLine());
//            System.out.println(5);
        }
//        System.out.println(6);
        String json = stringBuilder.toString();
//        System.out.println(7);
        ExpenseReimbursements er = objectMapper.readValue(json, ExpenseReimbursements.class);
        boolean checkER = service.checkER(er);
//        System.out.println(8);
//        System.out.println(er);
        if(checkER == true)
        {
            service.save(er);
            numb = 200;
        }

//        System.out.println(9);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(numb);


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numb =500;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = req.getReader();

        while(bufferedReader.ready())
        {
            stringBuilder.append(bufferedReader.readLine());
        }
        String json = stringBuilder.toString();
        ExpenseReimbursements er = objectMapper.readValue(json, ExpenseReimbursements.class);
        boolean checkER = service.checkER(er);
//        System.out.println(8);
//        System.out.println(er);
        if(checkER == true)
        {
            service.save(er);
            numb = 200;
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(numb);

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
