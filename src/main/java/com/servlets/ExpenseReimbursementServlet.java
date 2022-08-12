package com.servlets;

import com.pojos.ExpenseReimbursements;
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
    GOAL: to read one er or read all exp re based on if a paramter is read in (id )
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramID = req.getParameter("exp-id");
        if(paramID == null)
        {
            List<ExpenseReimbursements>  erAll = service.readAllER();
            String json = objectMapper.writeValueAsString(erAll);
            resp.getWriter().println(json);
        }
        else
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
        System.out.println(er);
        service.save(er);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
